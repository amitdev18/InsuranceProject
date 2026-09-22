package protecons.insurance.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lender")
public class Lender {

    @Id
    @Column(name = "lender_id", nullable = false, unique = true)
    private String lenderId;

    @Column(name = "lender_name", nullable = false)
    private String lenderName;

    @Column(name = "lender_account_number", nullable = false, unique = true)
    private String lenderAccountNumber;

    @Column(name = "loan_status", nullable = false)
    private String loanStatus;

    @Column(name = "original_loan_amount")
    private BigDecimal originalLoanAmount;

    @Column(name = "remaining_principal")
    private BigDecimal remainingPrincipal;


    @Column(name = "payoff_amount")
    private BigDecimal payoffAmount;

    @Column(name = "monthly_emi")
    private BigDecimal monthlyEmi;

    @Column(name = "emis_paid")
    private Integer emisPaid;

    @Column(name = "total_emis")
    private Integer totalEmis;

    @Column(name = "last_payment_id")
    private String lastPaymentId;

    @Column(name = "last_payment_date")
    private LocalDate lastPaymentDate;

    @Column(name = "currency", length = 3)
    private String currency;

    public Lender() {
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