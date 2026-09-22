package protecons.insurance.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import protecons.insurance.dto.auth.LoginRequest;
import protecons.insurance.dto.auth.LoginResponse;
import protecons.insurance.dto.auth.RegisterRequest;
import protecons.insurance.dto.auth.RegisterResponse;
import protecons.insurance.service.AuthService;

@Component("authProcessor")
public class AuthProcessor
        implements Processor {

    private final AuthService authService;

    public AuthProcessor(
            AuthService authService) {

        this.authService = authService;
    }

    @Override
    public void process(
            Exchange exchange) {

        String operation =
                exchange.getProperty(
                        "authOperation",
                        String.class
                );

        if ("REGISTER".equals(operation)) {

            RegisterRequest request =
                    exchange.getIn()
                            .getBody(
                                    RegisterRequest.class
                            );

            RegisterResponse response =
                    authService.register(request);

            exchange.getMessage()
                    .setBody(response);

            return;
        }

        if ("LOGIN".equals(operation)) {

            LoginRequest request =
                    exchange.getIn()
                            .getBody(
                                    LoginRequest.class
                            );

            LoginResponse response =
                    authService.login(request);

            exchange.getMessage()
                    .setBody(response);

            return;
        }

        throw new IllegalArgumentException(
                "Unsupported authentication operation"
        );
    }
}