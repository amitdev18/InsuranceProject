package protecons.insurance.service;

import org.springframework.stereotype.Service;
import protecons.insurance.dto.CustomerRequest;
import protecons.insurance.dto.CustomerResponse;
import protecons.insurance.entity.Customer;
import protecons.insurance.repository.CustomerRepository;

import java.time.Instant;

@Service("customerService")
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(CustomerRequest request) {

        // Check if email already exists
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException(
                    "Customer already exists with email: " + request.getEmail()
            );
        }

        // Create Entity
        Customer customer = new Customer();

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        // If your CustomerRequest contains address fields
        if (request.getAddress() != null) {
            customer.setStreet(request.getAddress().getStreet());
            customer.setCity(request.getAddress().getCity());
            customer.setState(request.getAddress().getState());
            customer.setZipCode(request.getAddress().getZipCode());
        }

        // Save to MySQL
        Customer savedCustomer = customerRepository.save(customer);

        // Create API response
        CustomerResponse response = new CustomerResponse();

        response.setCustomerId("CUS-" + savedCustomer.getId());
        response.setFirstName(savedCustomer.getFirstName());
        response.setLastName(savedCustomer.getLastName());
        response.setEmail(savedCustomer.getEmail());
        response.setPhone(savedCustomer.getPhone());
        response.setStatus("ACTIVE");
        response.setCreatedAt(Instant.now());

        return response;
    }
}