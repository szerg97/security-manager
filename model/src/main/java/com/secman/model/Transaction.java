package com.secman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Currency;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction extends GSecEntity {

    @Schema(description = "Currency of security")
    @NotBlank(message = "error.security.currency.not-blank")
    private Currency currency;
    @Schema(description = "Exchange rate of security")
    @NotBlank(message = "error.security.exchange-rate.not-blank")
    private Double exchangeRate;
    @Schema(description = "Face value of security")
    @NotBlank(message = "error.security.face-value.not-blank")
    private Double faceValue;
    @Schema(description = "Denomination of security")
    @NotBlank(message = "error.security.denomination.not-blank")
    private Double denomination;
    @Schema(description = "Gross value of security")
    @NotBlank(message = "error.security.gross-value.not-blank")
    private Double grossValue;
    @Schema(description = "Net value of security")
    @NotBlank(message = "error.security.net-value.not-blank")
    private Double netValue;
    @Schema(description = "Term of security")
    @NotBlank(message = "error.security.term.not-blank")
    private Double term;
    @Schema(description = "Interest of security")
    @NotBlank(message = "error.security.interest.not-blank")
    private Double interest;
    @Schema(description = "Accrued interest of security")
    @NotBlank(message = "error.security.accrued-interest.not-blank")
    private Double accruedInterest;
    @Schema(description = "Signs if the security is fixed or not")
    @NotBlank(message = "error.security.fixed-interest.not-blank")
    private Boolean fixedInterest;
    @Schema(description = "Yield of security")
    @NotBlank(message = "error.security.yield.not-blank")
    private Double yield;
    @Schema(description = "Reference yield of security")
    @NotBlank(message = "error.security.reference-yield.not-blank")
    private Double referenceYield;
    @Schema(description = "Category of security")
    @NotBlank(message = "error.security.category.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = SecurityCategory.class)
    private SecurityCategory category;
    @Schema(description = "Distributor of security")
    @NotBlank(message = "error.security.distributor.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = Distributor.class)
    private Distributor distributor;
    @Schema(description = "Portfolio that the sec belongs to")
    @NotBlank(message = "error.security.portfolio.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = Portfolio.class)
    private Portfolio portfolio;

    public Transaction(Currency currency, Double exchangeRate, Double faceValue, Double denomination, Double grossValue, Double netValue, Double term, Double interest, Double accruedInterest, Boolean fixedInterest, Double yield, Double referenceYield, SecurityCategory category, Distributor distributor, Portfolio portfolio) {
        super();
        this.currency = currency;
        this.exchangeRate = exchangeRate;
        this.faceValue = faceValue;
        this.denomination = denomination;
        this.grossValue = grossValue;
        this.netValue = netValue;
        this.term = term;
        this.interest = interest;
        this.accruedInterest = accruedInterest;
        this.fixedInterest = fixedInterest;
        this.yield = yield;
        this.referenceYield = referenceYield;
        this.category = category;
        this.distributor = distributor;
        this.portfolio = portfolio;
    }

    public void copyFrom(Transaction transaction) {
        this.currency = transaction.getCurrency();
        this.exchangeRate = transaction.getExchangeRate();
        this.faceValue = transaction.getFaceValue();
        this.denomination = transaction.getDenomination();
        this.grossValue = transaction.getGrossValue();
        this.netValue = transaction.getNetValue();
        this.term = transaction.getTerm();
        this.interest = transaction.getInterest();
        this.accruedInterest = transaction.getAccruedInterest();
        this.fixedInterest = transaction.getFixedInterest();
        this.yield = transaction.getYield();
        this.referenceYield = transaction.getReferenceYield();
        this.category = transaction.getCategory();
        this.distributor = transaction.getDistributor();
        this.portfolio = transaction.getPortfolio();
    }
}
