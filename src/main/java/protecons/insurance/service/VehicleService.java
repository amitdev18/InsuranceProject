package protecons.insurance.service;

import org.springframework.stereotype.Service;
import protecons.insurance.dto.vehicle.VehicleResponse;
import protecons.insurance.entity.Vehicle;
import protecons.insurance.repository.VehicleRepository;

@Service("vehicleService")
public class VehicleService {
private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

public VehicleResponse getVehicleByVin(String vin){
    Vehicle vehicle= vehicleRepository.findByVin(vin)
            .orElseThrow(()->
                 new RuntimeException("Vehicle Not Found"+ vin)
                    );

    VehicleResponse response=new VehicleResponse();
    response.setVin(vehicle.getVin());
    response.setVehicleId(vehicle.getVehicleId());
    response.setYear(vehicle.getYear());
    response.setMake(vehicle.getMake());
    response.setModel(vehicle.getModel());
    response.setTrim(vehicle.getTrim());
    response.setColor(vehicle.getColor());
    response.setVehicleType(vehicle.getVehicleType());

    // Business rule
    response.setTotalLoss(true);

    response.setLenderId(vehicle.getLenderId());
    response.setTitleStatus(vehicle.getTitleStatus());
    response.setRegistrationState(vehicle.getRegistrationState());

    return response;
}

}
