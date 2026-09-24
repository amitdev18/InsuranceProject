package protecons.insurance.dto.lender;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LenderResponse {
    private String lenderId;

    private String lenderName;
    private String lenderAccountNumber;
    private String loanStatus;
    private BigDecimal originalLoanAmount;
    private BigDecimal remainingPrincipal;
    private BigDecimal payoffAmount;

    private BigDecimal monthlyEmi;

    private Integer emisPaid;

    private Integer totalEmis;

    private String lastPaymentId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate lastPaymentDate;
    private String currency;

    public LenderResponse(String lenderId, String lenderName, String lenderAccountNumber, String loanStatus, BigDecimal originalLoanAmount, BigDecimal remainingPrincipal, BigDecimal payoffAmount, BigDecimal monthlyEmi, Integer emisPaid, Integer totalEmis, String lastPaymentId, LocalDate lastPaymentDate, String currency) {
        this.lenderId = lenderId;
        this.lenderName = lenderName;
        this.lenderAccountNumber = lenderAccountNumber;
        this.loanStatus = loanStatus;
        this.originalLoanAmount = originalLoanAmount;
        this.remainingPrincipal = remainingPrincipal;
        this.payoffAmount = payoffAmount;
        this.monthlyEmi = monthlyEmi;
        this.emisPaid = emisPaid;
        this.totalEmis = totalEmis;
        this.lastPaymentId = lastPaymentId;
        this.lastPaymentDate = lastPaymentDate;
        this.currency = currency;
    }

    public LenderResponse() {
    }

    public String getLenderId() {
        return lenderId;
    }

    public void setLenderId(String lenderId) {
        this.lenderId = lenderId;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public String getLenderAccountNumber() {
        return lenderAccountNumber;
    }

    public void setLenderAccountNumber(String lenderAccountNumber) {
        this.lenderAccountNumber = lenderAccountNumber;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public BigDecimal getOriginalLoanAmount() {
        return originalLoanAmount;
    }

    public void setOriginalLoanAmount(BigDecimal originalLoanAmount) {
        this.originalLoanAmount = originalLoanAmount;
    }

    public BigDecimal getRemainingPrincipal() {
        return remainingPrincipal;
    }

    public void setRemainingPrincipal(BigDecimal remainingPrincipal) {
        this.remainingPrincipal = remainingPrincipal;
    }

    public BigDecimal getPayoffAmount() {
        return payoffAmount;
    }

    public void setPayoffAmount(BigDecimal payoffAmount) {
        this.payoffAmount = payoffAmount;
    }

    public BigDecimal getMonthlyEmi() {
        return monthlyEmi;
    }

    public void setMonthlyEmi(BigDecimal monthlyEmi) {
        this.monthlyEmi = monthlyEmi;
    }

    public Integer getEmisPaid() {
        return emisPaid;
    }

    public void setEmisPaid(Integer emisPaid) {
        this.emisPaid = emisPaid;
    }

    public Integer getTotalEmis() {
        return totalEmis;
    }

    public void setTotalEmis(Integer totalEmis) {
        this.totalEmis = totalEmis;
    }

    public String getLastPaymentId() {
        return lastPaymentId;
    }

    public void setLastPaymentId(String lastPaymentId) {
        this.lastPaymentId = lastPaymentId;
    }

    public LocalDate getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(LocalDate lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
