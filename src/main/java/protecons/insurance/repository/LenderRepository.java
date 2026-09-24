package protecons.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import protecons.insurance.entity.Lender;

import java.util.Optional;

public interface LenderRepository extends JpaRepository<Lender, String> {
    Optional<Lender> findByLenderId(String lenderId);


}
