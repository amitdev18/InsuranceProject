package protecons.insurance.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import protecons.insurance.dto.lender.LenderResponse;

@Component
public class LenderRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        rest("/api/v1/lenders")
                .get("/{lenderId}")
                .description("get lender details by lender id")
                .param()
                  .name("lenderId")
                  .required(true)
                .endParam()
                .bindingMode(RestBindingMode.json)
                .produces("application/json")
                .outType(LenderResponse.class)
                .to("direct:lender");


        from("direct:lender")
                .routeId("lender-route")
                .log("lender request received")
                .process("lenderProcessor")
                .log("Lender information received: ${body}");
    }
}
