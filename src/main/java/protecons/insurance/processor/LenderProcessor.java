package protecons.insurance.processor;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import protecons.insurance.dto.lender.LenderResponse;
import protecons.insurance.service.LenderService;


@Component("lenderProcessor")

public class LenderProcessor implements Processor {

    private final LenderService lenderService;
    private final ObjectMapper objectMapper;

    public LenderProcessor(LenderService lenderService, ObjectMapper objectMapper) {
        this.lenderService = lenderService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        String lenderId = exchange.getMessage().getHeader("lenderId", String.class);

        LenderResponse lenderResponse = lenderService.getLenderByLenderID(lenderId);

        // Explicitly convert POJO to JSON String using Spring's configured ObjectMapper
        String jsonBody = objectMapper.writeValueAsString(lenderResponse);

        exchange.getMessage().setHeader(Exchange.CONTENT_TYPE, "application/json");

        exchange.getMessage().setBody(jsonBody);


    }
}
