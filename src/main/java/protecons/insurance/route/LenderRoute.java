package protecons.insurance.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import protecons.insurance.dto.lender.LenderResponse;

@Component
public class LenderRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(false)
                .process(exchange -> {
                    Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
                    System.err.println("CAMEL ROUTE EXCEPTION");
                    if (cause != null) {
                        cause.printStackTrace();
                    } else {
                        System.err.println("No exception object found on exchange.");
                    }
                    System.err.println("===========");
                });


        rest("/api/v1/lenders")
                .get("/{lenderId}")
                .description("get lender details by lender id")
                .param()
                .name("lenderId")
                .required(true)
                .endParam()
                .produces("application/json")
                .outType(LenderResponse.class)
                .security("bearerAuth")
                .to("direct:lender");


        from("direct:lender")
                .routeId("lender-route")
                .log("lender request received")
                .process("lenderProcessor")
                .log("Lender information received: ${body}");
    }
}
