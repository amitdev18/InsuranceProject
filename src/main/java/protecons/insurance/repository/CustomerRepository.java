package protecons.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import protecons.insurance.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String Email);

    boolean existsByEmail(String email);
}