package protecons.insurance.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import protecons.insurance.dto.vehicle.VehicleResponse;
import protecons.insurance.service.VehicleService;

@Component("vehicleProcessor")
public class VehicleProcessor implements Processor {
    private final VehicleService vehicleService;

    public VehicleProcessor(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @Override
    public void process(Exchange exchange) throws Exception {

        String vin = exchange.getIn().getHeader("vin", String.class);

        VehicleResponse vehicleResponse = vehicleService.getVehicleByVin(vin);
        exchange.getIn().setBody(vehicleResponse);

    }
}
