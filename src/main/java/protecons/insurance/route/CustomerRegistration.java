package protecons.insurance.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import protecons.insurance.processor.CustomerProcessor;

@Component
public class CustomerRegistration extends RouteBuilder {
    private CustomerProcessor customerProcessor;
    public void customerProcessor(CustomerProcessor customerProcessor) {
        this.customerProcessor = customerProcessor;
    }

    @Override
    public void configure() throws Exception {

        from("direct:createCustomer")
                .routeId("customer-registration-route")
                .log("customer registration request receiver")
                .process("customerProcessor")
                .log("Customer created successfully");

    }
}

