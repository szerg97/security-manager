package com.secman.application.dto;

import java.time.LocalDateTime;

public class TransactionDto {
    private Long id;
    private Double denomination;
    private Double grossValue;
    private Double netValue;
    private Double yield;
    private Double referenceYield;
    private Long securityId;
    private Long issuerId;
    private Long portfolioId;
    private LocalDateTime inserted;

    public TransactionDto() {
    }

    public TransactionDto(Long id, Double denomination, Double grossValue, Double netValue, Double yield, Double referenceYield, Long securityId, Long issuerId, Long portfolioId, LocalDateTime inserted) {
        this.id = id;
        this.denomination = denomination;
        this.grossValue = grossValue;
        this.netValue = netValue;
        this.yield = yield;
        this.referenceYield = referenceYield;
        this.securityId = securityId;
        this.issuerId = issuerId;
        this.portfolioId = portfolioId;
        this.inserted = inserted;
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

    public Double getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(Double grossValue) {
        this.grossValue = grossValue;
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

    public Long getSecurityId() {
        return securityId;
    }

    public void setSecurityId(Long securityId) {
        this.securityId = securityId;
    }

    public Long getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(Long issuerId) {
        this.issuerId = issuerId;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public LocalDateTime getInserted() {
        return inserted;
    }

    public void setInserted(LocalDateTime inserted) {
        this.inserted = inserted;
    }
}
