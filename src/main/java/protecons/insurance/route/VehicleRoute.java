package protecons.insurance.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import protecons.insurance.dto.vehicle.VehicleResponse;

@Component
public class VehicleRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("/api/v1/vehicle")
                .get("/{vin}")
                .description("receive vehicle information by vin")
                .param()
                .name("vin")
                .required(true)
                .endParam()
                .bindingMode(RestBindingMode.json)
                .produces("application/json")
                .consumes("application/json")
                .outType(VehicleResponse.class)
                .security("bearerAuth")
                .to("direct:vehicle");

        from("direct:vehicle")
                .routeId("vehicle-route")
                .log("Vehicle request received")
                .process("vehicleProcessor")
                .log("Vehicle created");
    }
}
