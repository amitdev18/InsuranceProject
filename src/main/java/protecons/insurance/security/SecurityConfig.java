package protecons.insurance.security;


import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter =
                jwtAuthenticationFilter;

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http

                // JWT APIs don't use CSRF

                .csrf(csrf ->
                        csrf.disable()
                )

                // JWT is stateless

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth
                        // Allow Async, Forward, Error dispatches
                        .dispatcherTypeMatchers(
                                DispatcherType.FORWARD,
                                DispatcherType.ASYNC,
                                DispatcherType.ERROR
                        ).permitAll()

                        .requestMatchers(
                                "/api/v1/auth/register"
                        ).permitAll()

                        .requestMatchers(
                                "/api/v1/auth/login"
                        ).permitAll()

                        // Swagger

                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // customer api
                        .requestMatchers(
                                "/api/v1/customer"
                        ).permitAll()

                        // VEHICLE API

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/v1/vehicles/**"
                        ).hasAnyRole(
                                "ADMIN",
                                "INSURANCE_AGENT",
                                "CUSTOMER",
                                "LENDER",
                                "SCARP_AGENT"
                        )

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/v1/lenders/**"
                        )
                        .hasAnyRole(
                                "ADMIN",
                                "INSURANCE_AGENT",
                                "LENDER",
                                "CUSTOMER",
                                "SCARP_AGENT"
                        )

                        // Everything else requires authentication

                        .anyRequest()
                        .authenticated()
                )
                // JWT FILTER
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                // 401 / 403
                .exceptionHandling(exception -> exception

                        .authenticationEntryPoint(
                                (request, response, authException) -> {

                                    // Print the exact exception in console to debug 401 errors
                                    System.err.println("=== 401 UNAUTHORIZED TRIGGERED ===");
                                    authException.printStackTrace();

                                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                    response.setContentType("application/json");
                                    response.getWriter().write(
                                            "{\"error\":\"Unauthorized\",\"message\":\"Authentication required\"}"
                                    );
                                }
                        )

                        .accessDeniedHandler(
                                (request,
                                 response,
                                 accessDeniedException) -> {

                                    response.setStatus(
                                            HttpServletResponse
                                                    .SC_FORBIDDEN
                                    );

                                    response.setContentType(
                                            "application/json"
                                    );

                                    response.getWriter()
                                            .write(
                                                    "{\"error\":\"Forbidden\",\"message\":\"Insufficient permissions\"}"
                                            );
                                }
                        )
                );

        return http.build();
    }
}
