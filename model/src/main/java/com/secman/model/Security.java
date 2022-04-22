package com.secman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Currency;
import java.util.List;

@Entity
@Table(name = "securities")
@Getter
@Setter
@NoArgsConstructor
public class Security extends GSecEntity {

    @Schema(description = "Exchange rate of transaction")
    @NotBlank(message = "error.transaction.exchange-rate.not-blank")
    private Double exchangeRate;
    @Schema(description = "Name of security")
    @NotBlank(message = "error.security.name.not-blank")
    @Size(min = 2, max = 128, message = "error.security.name.size")
    private String name;
    @Schema(description = "Description of security")
    @NotBlank(message = "error.security.description.not-blank")
    @Size(min = 2, max = 65535, message = "error.security.description.size")
    private String description;
    @Schema(description = "Currency of security")
    @NotBlank(message = "error.security.currency.not-blank")
    private Currency currency;
    @Schema(description = "Face value of security")
    @NotBlank(message = "error.security.face-value.not-blank")
    private Double faceValue;
    @Schema(description = "Accrued interest of security")
    @NotBlank(message = "error.security.accrued-interest.not-blank")
    private Double accruedInterest;
    @Schema(description = "Interest of security")
    @NotBlank(message = "error.security.interest.not-blank")
    private Double interest;
    @Schema(description = "Signs if the security is fixed or not")
    @NotBlank(message = "error.security.fixed-interest.not-blank")
    private Boolean fixedInterest;
    @Schema(description = "Term of security")
    @NotBlank(message = "error.security.term.not-blank")
    private Double term;

    @JsonIgnore
    @OneToMany(targetEntity = Transaction.class, mappedBy = "security")
    private List<Transaction> transactions;

    public Security(String name, String description, Currency currency, Double faceValue, Double accruedInterest, Double interest, Boolean fixedInterest, Double term) {
        super();
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.faceValue = faceValue;
        this.accruedInterest = accruedInterest;
        this.interest = interest;
        this.fixedInterest = fixedInterest;
        this.exchangeRate = this.accruedInterest + 1;
        this.term = term;
    }

    public void copyFrom(Security category) {
        this.name = category.getName();
        this.description = category.getDescription();
        this.currency = category.getCurrency();
        this.faceValue = category.getFaceValue();
        this.accruedInterest = category.getAccruedInterest();
        this.interest = category.getInterest();
        this.fixedInterest = category.getFixedInterest();
        this.exchangeRate = category.getExchangeRate();
        this.term = category.getTerm();
    }
}
