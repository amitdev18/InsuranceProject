package protecons.insurance.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import protecons.insurance.entity.UserToken;
import protecons.insurance.repository.UserTokenRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final CustomUserDetailsService userDetailsService;

    private final UserTokenRepository userTokenRepository;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            CustomUserDetailsService userDetailsService,
            UserTokenRepository userTokenRepository) {

        this.jwtService = jwtService;

        this.userDetailsService =
                userDetailsService;

        this.userTokenRepository =
                userTokenRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader =
                request.getHeader(
                        "Authorization"
                );

        /*
         * No Authorization header.
         *
         * We don't immediately send 401 here.
         * Spring Security will handle it when the
         * endpoint requires authentication.
         */

        if (authorizationHeader == null ||
                !authorizationHeader.startsWith(
                        "Bearer "
                )) {

            filterChain.doFilter(
                    request,
                    response
            );

            return;
        }

        String token =
                authorizationHeader.substring(7);

        try {

            // Validate JWT signature and expiration

            if (!jwtService.isTokenValid(token)) {

                SecurityContextHolder.clearContext();

                filterChain.doFilter(
                        request,
                        response
                );

                return;
            }

            // Check token exists in SQL

            Optional<UserToken> storedToken =
                    userTokenRepository
                            .findByTokenAndRevokedFalse(
                                    token
                            );

            if (storedToken.isEmpty()) {

                SecurityContextHolder.clearContext();

                filterChain.doFilter(
                        request,
                        response
                );

                return;
            }

            UserToken userToken =
                    storedToken.get();

            // Check DB token expiration

            if (userToken.getExpiresAt()
                    .isBefore(
                            LocalDateTime.now()
                    )) {

                SecurityContextHolder.clearContext();

                filterChain.doFilter(
                        request,
                        response
                );

                return;
            }

            String email =
                    jwtService.extractEmail(token);

            if (email == null ||
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication() != null) {

                filterChain.doFilter(
                        request,
                        response
                );

                return;
            }

            UserDetails userDetails =
                    userDetailsService
                            .loadUserByUsername(
                                    email
                            );

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(

                            userDetails,

                            null,

                            userDetails.getAuthorities()
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(
                            authentication
                    );

        } catch (Exception e) {

            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(
                request,
                response
        );
    }
}
