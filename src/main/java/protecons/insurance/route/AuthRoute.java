package protecons.insurance.route;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import protecons.insurance.dto.auth.LoginRequest;
import protecons.insurance.dto.auth.LoginResponse;
import protecons.insurance.dto.auth.RegisterRequest;
import protecons.insurance.dto.auth.RegisterResponse;

@Component
public class AuthRoute
        extends RouteBuilder {

    @Override
    public void configure()
            throws Exception {

        // AUTH REST API

        rest("/api/v1/auth")

                // REGISTER

                .post("/register")

                .description(
                        "Register a new insurance user"
                )

                .consumes("application/json")

                .produces("application/json")

                .type(RegisterRequest.class)

                .outType(RegisterResponse.class)

                .bindingMode(
                        RestBindingMode.json
                )

                .to("direct:register")

                // LOGIN

                .post("/login")

                .description(
                        "Authenticate user and generate JWT"
                )

                .consumes("application/json")

                .produces("application/json")

                .type(LoginRequest.class)

                .outType(LoginResponse.class)

                .bindingMode(
                        RestBindingMode.json
                )

                .to("direct:login");

        // REGISTER ROUTE

        from("direct:register")

                .routeId(
                        "user-registration-route"
                )

                .setProperty(
                        "authOperation",
                        constant("REGISTER")
                )

                .log(
                        "User registration request received"
                )

                .process("authProcessor")

                .log(
                        "User registration completed"
                );

        // LOGIN ROUTE

        from("direct:login")

                .routeId(
                        "authentication-route"
                )

                .setProperty(
                        "authOperation",
                        constant("LOGIN")
                )

                .log(
                        "User login request received"
                )

                .process("authProcessor")

                .log(
                        "User login completed"
                );
    }
}
