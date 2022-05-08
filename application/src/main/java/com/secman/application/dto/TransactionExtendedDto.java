package com.secman.application.dto;

import java.time.LocalDateTime;

public class TransactionExtendedDto {

    private Long id;
    private Double denomination;
    private Double netValue;
    private Double yield;
    private Double referenceYield;
    private LocalDateTime inserted;

    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private LocalDateTime registered;

    private String securityName;
    private String issuerName;

    public TransactionExtendedDto(Long id, Double denomination, Double netValue, Double yield, Double referenceYield, LocalDateTime inserted, String customerName, String customerEmail, String customerPhone, LocalDateTime registered, String securityName, String issuerName) {
        this.id = id;
        this.denomination = denomination;
        this.netValue = netValue;
        this.yield = yield;
        this.referenceYield = referenceYield;
        this.inserted = inserted;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.registered = registered;
        this.securityName = securityName;
        this.issuerName = issuerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDenomination() {
        return denomination;
    }

    public void setDenomination(Double denomination) {
        this.denomination = denomination;
    }

    public Double getNetValue() {
        return netValue;
    }

    public void setNetValue(Double netValue) {
        this.netValue = netValue;
    }

    public Double getYield() {
        return yield;
    }

    public void setYield(Double yield) {
        this.yield = yield;
    }

    public Double getReferenceYield() {
        return referenceYield;
    }

    public void setReferenceYield(Double referenceYield) {
        this.referenceYield = referenceYield;
    }

    public LocalDateTime getInserted() {
        return inserted;
    }

    public void setInserted(LocalDateTime inserted) {
        this.inserted = inserted;
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

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public String getSecurityName() {
        return securityName;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }
}
