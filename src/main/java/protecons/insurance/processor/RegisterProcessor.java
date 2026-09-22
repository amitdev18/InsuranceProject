//package protecons.insurance.processor;
//
//import org.apache.camel.Exchange;
//import org.apache.camel.Processor;
//import protecons.insurance.dto.auth.RegisterRequest;
//import protecons.insurance.dto.auth.RegisterResponse;
//
//public class RegisterProcessor implements Processor {
//    private final AuthService authService;
//
//    public RegisterProcessor(AuthService authService) {
//        this.authService = authService;
//    }
//    @Override
//    public void process(Exchange exchange) throws Exception {
//        RegisterRequest request =
//                exchange.getIn().getBody(RegisterRequest.class);
//
//        RegisterResponse response =
//                authService.register(request);
//
//        exchange.getMessage().setBody(response);
//
//
//    }
//}
