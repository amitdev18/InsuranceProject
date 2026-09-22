package protecons.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import protecons.insurance.entity.Vehicle;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    Optional<Vehicle>findByVin(String vin);
    boolean existsByVin(String vin);

}
