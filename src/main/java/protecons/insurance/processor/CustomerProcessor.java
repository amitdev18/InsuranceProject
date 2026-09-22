package protecons.insurance.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import protecons.insurance.dto.CustomerRequest;
import protecons.insurance.dto.CustomerResponse;
import protecons.insurance.service.CustomerService;


@Component("customerProcessor")
public class CustomerProcessor implements Processor {
 private final CustomerService customerService;
    public CustomerProcessor(CustomerService customerService) {
        this.customerService = customerService;
    }
    @Override
    public void process(Exchange exchange) throws Exception {
        CustomerRequest customerRequest = exchange.getIn().getBody(CustomerRequest.class);
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        exchange.getIn().setBody(customerResponse);

    }
}
