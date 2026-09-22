package protecons.insurance.controller;

import jakarta.validation.Valid;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import protecons.insurance.dto.CustomerRequest;
import protecons.insurance.dto.CustomerResponse;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final ProducerTemplate producerTemplate;

    public CustomerController(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @PostMapping()
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerRequest request) {
        CustomerResponse response = producerTemplate.requestBody(
                "direct:createCustomer",
                request,
                CustomerResponse.class
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}

