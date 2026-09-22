package protecons.insurance.service;

import org.springframework.stereotype.Service;
import protecons.insurance.dto.lender.LenderResponse;
import protecons.insurance.entity.Lender;
import protecons.insurance.repository.LenderRepository;

@Service("lenderService")
public class LenderService {
    private final LenderRepository lenderRepository;
    public LenderService(LenderRepository lenderRepository) {
        this.lenderRepository = lenderRepository;
    }

    public LenderResponse getLenderByLenderID(String lenderId) {

        Lender lender = lenderRepository.findByLenderId(lenderId)
                .orElseThrow(() ->
                        new RuntimeException("lender id not found" + lenderId)
                );
        LenderResponse lenderResponse = new LenderResponse();

        lenderResponse.setLenderId(lender.getLenderId());
        lenderResponse.setLenderName(lender.getLenderName());
        lenderResponse.setGetLenderAccountNumber(lender.getLenderAccountNumber());
        lenderResponse.setLoanStatus(lender.getLoanStatus());
        lenderResponse.setOriginalLoanAmount(lender.getOriginalLoanAmount());
        lenderResponse.setRemainingAmount(lender.getRemainingPrincipal());
        lenderResponse.setPayoffAmount(lender.getPayoffAmount());
        lenderResponse.setMonthlyEmi(lender.getMonthlyEmi());
        lenderResponse.setEmisPaid(lender.getEmisPaid());
        lenderResponse.setTotalEmi(lender.getTotalEmis());
        lenderResponse.setLastPaymentId(lender.getLastPaymentId());
        lenderResponse.setLastPaymentDate(lender.getLastPaymentDate());
        lenderResponse.setCurrency(lender.getCurrency());


        return lenderResponse;


    }


}
