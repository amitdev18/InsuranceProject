package protecons.insurance.processor;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import protecons.insurance.dto.lender.LenderResponse;
import protecons.insurance.service.LenderService;

@Component("lenderProcessor")
public class LenderProcessor implements Processor{

    private final LenderService lenderService;

    public LenderProcessor(LenderService lenderService) {
        this.lenderService = lenderService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        String lenderId=exchange.getMessage().getHeader("lenderId", String.class);

        LenderResponse lenderResponse= lenderService.getLenderByLenderID(lenderId);
        exchange.getMessage().setBody(lenderResponse);

        System.out.println("response created"+ lenderResponse);

    }
}
