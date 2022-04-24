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

    @Schema(description = "Denomination of transaction")
    @NotBlank(message = "error.transaction.denomination.not-blank")
    private Double denomination;
    @Schema(description = "Gross value of transaction")
    @NotBlank(message = "error.transaction.gross-value.not-blank")
    private Double netValue;
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

    public Transaction(Security security, Issuer issuer, Portfolio portfolio, Double denomination) {
        super();
        this.security = security;
        this.issuer = issuer;
        this.portfolio = portfolio;
        this.denomination = denomination;
        this.netValue = this.denomination * this.security.getExchangeRate();
        if (this.security.getFixedInterest())
            this.yield = netPresentValueFixed();
        else
            this.yield = netPresentValueFloating();
        this.referenceYield = this.yield;
    }

    public Double netPresentValueFloating(){
        Double res = 0.0;
        for (int i = 1; i <= this.security.getTerm() * this.security.getFrequency(); i++){
            res += (this.denomination + res) / Math.pow((1 + this.security.getInterest()), i) ;
        }

        return res - this.netValue;
    }

    public Double netPresentValueFixed(){
        Double res = 0.0;
        for (int i = 1; i <= this.security.getTerm() * this.security.getFrequency(); i++){
            res += this.denomination / Math.pow((1 + this.security.getInterest()), i);
        }

        return res - this.netValue;
    }

    public void copyFrom(Transaction transaction) {
        this.denomination = transaction.getDenomination();
        this.netValue = transaction.getNetValue();
        this.yield = transaction.getYield();
        this.referenceYield = transaction.getReferenceYield();
        this.security = transaction.getSecurity();
        this.issuer = transaction.getIssuer();
        this.portfolio = transaction.getPortfolio();
    }
}
