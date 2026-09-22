package protecons.insurance.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import protecons.insurance.constant.Roles;
import protecons.insurance.dto.auth.LoginRequest;
import protecons.insurance.dto.auth.LoginResponse;
import protecons.insurance.dto.auth.RegisterRequest;
import protecons.insurance.dto.auth.RegisterResponse;
import protecons.insurance.entity.User;
import protecons.insurance.entity.UserToken;
import protecons.insurance.exceptions.DuplicateEmailException;
import protecons.insurance.exceptions.InvalidCredentialsException;
import protecons.insurance.repository.UserRepository;
import protecons.insurance.repository.UserTokenRepository;
import protecons.insurance.security.JwtService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final UserTokenRepository userTokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            UserTokenRepository userTokenRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository =
                userRepository;

        this.userTokenRepository =
                userTokenRepository;

        this.passwordEncoder =
                passwordEncoder;

        this.jwtService =
                jwtService;
    }

    // =====================================================
    // REGISTER
    // =====================================================

    @Transactional
    public RegisterResponse register(
            RegisterRequest request) {

        // Validate request

        if (request.getEmail() == null ||
                request.getEmail().isBlank()) {

            throw new IllegalArgumentException(
                    "Email is required"
            );
        }

        if (request.getPassword() == null ||
                request.getPassword().isBlank()) {

            throw new IllegalArgumentException(
                    "Password is required"
            );
        }

        if (request.getPassword().length() < 8) {

            throw new IllegalArgumentException(
                    "Password must contain at least 8 characters"
            );
        }

        // Normalize email

        String email =
                request.getEmail()
                        .trim()
                        .toLowerCase();

        // Check duplicate email

        if (userRepository.existsByEmail(email)) {

            throw new DuplicateEmailException(
                    "Email is already registered"
            );
        }

        // Create User

        User user = new User();

        /*
         * Generate dynamic user ID.
         *
         * Example:
         * USR-8A31F204
         */

        String userId =
                "USR-" +
                        UUID.randomUUID()
                                .toString()
                                .substring(0, 8)
                                .toUpperCase();

        user.setUserId(userId);

        user.setFirstName(
                request.getFirstName()
        );

        user.setLastName(
                request.getLastName()
        );

        user.setEmail(email);

        /*
         * NEVER store the plain password.
         */

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        /*
         * IMPORTANT:
         *
         * Role is NOT received from the client.
         *
         * Normal public registration creates
         * an INSURANCE_AGENT.
         */

        user.setRole(
                Roles.INSURANCE_AGENT
        );

        user.setEnabled(true);

        // Save to SQL

        User savedUser =
                userRepository.save(user);

        // Response

        return new RegisterResponse(

                savedUser.getUserId(),

                "User registered successfully",

                savedUser.getEmail(),

                savedUser.getRole().name()
        );
    }

    // =====================================================
    // LOGIN
    // =====================================================

    @Transactional
    public LoginResponse login(
            LoginRequest request) {

        if (request.getEmail() == null ||
                request.getEmail().isBlank()) {

            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        if (request.getPassword() == null ||
                request.getPassword().isBlank()) {

            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        String email =
                request.getEmail()
                        .trim()
                        .toLowerCase();

        // Find user

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () ->
                                        new InvalidCredentialsException(
                                                "Invalid email or password"
                                        )
                        );

        // Check account

        if (!Boolean.TRUE.equals(
                user.getEnabled())) {

            throw new InvalidCredentialsException(
                    "User account is disabled"
            );
        }

        // Check password

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatches) {

            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        // Generate JWT

        String token =
                jwtService.generateToken(

                        user.getUserId(),

                        user.getEmail(),

                        user.getRole().name()
                );

        // Store token

        UserToken userToken =
                new UserToken();

        userToken.setToken(token);

        userToken.setUserId(
                user.getUserId()
        );

        userToken.setCreatedAt(
                LocalDateTime.now()
        );

        userToken.setExpiresAt(
                LocalDateTime.now()
                        .plusSeconds(
                                jwtService
                                        .getExpirationInSeconds()
                        )
        );

        userToken.setRevoked(false);

        userTokenRepository.save(
                userToken
        );

        // Return login response

        return new LoginResponse(

                token,

                "Bearer",

                jwtService
                        .getExpirationInSeconds(),

                user.getUserId(),

                user.getRole().name()
        );
    }
}
