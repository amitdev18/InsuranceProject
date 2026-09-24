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
        lenderResponse.setLenderAccountNumber(lender.getLenderAccountNumber());
        lenderResponse.setLoanStatus(lender.getLoanStatus());
        lenderResponse.setOriginalLoanAmount(lender.getOriginalLoanAmount());
        lenderResponse.setRemainingPrincipal(lender.getRemainingPrincipal());
        lenderResponse.setPayoffAmount(lender.getPayoffAmount());
        lenderResponse.setMonthlyEmi(lender.getMonthlyEmi());
        lenderResponse.setEmisPaid(lender.getEmisPaid());
        lenderResponse.setTotalEmis(lender.getTotalEmis());
        lenderResponse.setLastPaymentId(lender.getLastPaymentId());
        lenderResponse.setLastPaymentDate(lender.getLastPaymentDate());
        lenderResponse.setCurrency(lender.getCurrency());

        return new LenderResponse(lenderResponse.getLenderId(), lenderResponse.getLenderName(), lenderResponse.getLenderAccountNumber(), lenderResponse.getLoanStatus(), lenderResponse.getOriginalLoanAmount(), lenderResponse.getRemainingPrincipal(), lenderResponse.getPayoffAmount(), lenderResponse.getMonthlyEmi(), lenderResponse.getEmisPaid(), lenderResponse.getTotalEmis(), lenderResponse.getLastPaymentId(), lenderResponse.getLastPaymentDate(), lenderResponse.getCurrency());


    }


}
