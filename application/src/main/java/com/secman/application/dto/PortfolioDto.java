package com.secman.application.dto;

import com.secman.model.Customer;
import com.secman.model.Transaction;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

public class PortfolioDto {

    private Double balance;
    private Double securitiesTotal;
    private Double denominationTotal;
    private Double total;
    private String customerName;
    private String customerEmail;

    public PortfolioDto(Double balance, Double securitiesTotal, Double denominationTotal, String customerName, String customerEmail) {
        this.balance = balance;
        this.securitiesTotal = securitiesTotal;
        this.denominationTotal = denominationTotal;
        this.total = this.balance + this.securitiesTotal;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getSecuritiesTotal() {
        return securitiesTotal;
    }

    public void setSecuritiesTotal(Double securitiesTotal) {
        this.securitiesTotal = securitiesTotal;
    }

    public Double getDenominationTotal() {
        return denominationTotal;
    }

    public void setDenominationTotal(Double denominationTotal) {
        this.denominationTotal = denominationTotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
