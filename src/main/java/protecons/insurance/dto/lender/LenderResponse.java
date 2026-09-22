package protecons.insurance.dto.lender;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LenderResponse {
    private String lenderId;

    private String lenderName;
    private String getLenderAccountNumber;
    private String loanStatus;
    private BigDecimal originalLoanAmount;
    private BigDecimal remainingAmount;
    private BigDecimal payoffAmount;

    private BigDecimal monthlyEmi;

    private Integer emisPaid;

    private Integer totalEmi;

    private String lastPaymentId;

    private LocalDate lastPaymentDate;

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

    public String getGetLenderAccountNumber() {
        return getLenderAccountNumber;
    }

    public void setGetLenderAccountNumber(String getLenderAccountNumber) {
        this.getLenderAccountNumber = getLenderAccountNumber;
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

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
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

    public Integer getTotalEmi() {
        return totalEmi;
    }

    public void setTotalEmi(Integer totalEmi) {
        this.totalEmi = totalEmi;
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

    private String currency;

}
