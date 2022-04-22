package com.secman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction extends GSecEntity {

    @Schema(description = "Exchange rate of transaction")
    @NotBlank(message = "error.transaction.exchange-rate.not-blank")
    private Double exchangeRate;
    @Schema(description = "Denomination of transaction")
    @NotBlank(message = "error.transaction.denomination.not-blank")
    private Double denomination;
    @Schema(description = "Gross value of transaction")
    @NotBlank(message = "error.transaction.gross-value.not-blank")
    private Double grossValue;
    @Schema(description = "Net value of transaction")
    @NotBlank(message = "error.transaction.net-value.not-blank")
    private Double netValue;
    @Schema(description = "Term of transaction")
    @NotBlank(message = "error.transaction.term.not-blank")
    private Double term;
    @Schema(description = "Accrued interest of transaction")
    @NotBlank(message = "error.transaction.accrued-interest.not-blank")
    private Double accruedInterest;
    @Schema(description = "Yield of transaction")
    @NotBlank(message = "error.transaction.yield.not-blank")
    private Double yield;
    @Schema(description = "Reference yield of transaction")
    @NotBlank(message = "error.transaction.reference-yield.not-blank")
    private Double referenceYield;
    @Schema(description = "Security of transaction")
    @NotBlank(message = "error.transaction.security.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = Security.class)
    private Security security;
    @Schema(description = "Issuer of transaction")
    @NotBlank(message = "error.transaction.issuer.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = Issuer.class)
    private Issuer issuer;
    @Schema(description = "Portfolio that the transaction belongs to")
    @NotBlank(message = "error.transaction.portfolio.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = Portfolio.class)
    private Portfolio portfolio;

    public Transaction(Security security, Issuer issuer, Portfolio portfolio, Double accruedInterest, Double denomination, Double term) {
        super();
        this.security = security;
        this.issuer = issuer;
        this.portfolio = portfolio;
        this.accruedInterest = accruedInterest;
        this.exchangeRate = this.accruedInterest + 1;
        this.denomination = denomination;
        this.term = term;
        this.netValue = this.denomination * this.exchangeRate;
        this.grossValue = this.netValue  + (this.netValue * this.security.getInterest() * this.term);
        this.yield = this.grossValue - this.netValue;
        this.referenceYield = this.yield;
    }

    public void copyFrom(Transaction transaction) {
        this.denomination = transaction.getDenomination();
        this.grossValue = transaction.getGrossValue();
        this.netValue = transaction.getNetValue();
        this.term = transaction.getTerm();
        this.yield = transaction.getYield();
        this.referenceYield = transaction.getReferenceYield();
        this.accruedInterest = transaction.getAccruedInterest();
        this.security = transaction.getSecurity();
        this.issuer = transaction.getIssuer();
        this.portfolio = transaction.getPortfolio();
    }
}
