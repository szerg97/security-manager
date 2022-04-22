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
    @Schema(description = "Interest of security")
    @NotBlank(message = "error.security.interest.not-blank")
    private Double interest;
    @Schema(description = "Signs if the security is fixed or not")
    @NotBlank(message = "error.security.fixed-interest.not-blank")
    private Boolean fixedInterest;

    @JsonIgnore
    @OneToMany(targetEntity = Transaction.class, mappedBy = "security")
    private List<Transaction> transactions;

    public Security(String name, String description, Currency currency, Double faceValue, Double interest, Boolean fixedInterest) {
        super();
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.faceValue = faceValue;
        this.interest = interest;
        this.fixedInterest = fixedInterest;
    }

    public void copyFrom(Security category) {
        this.name = category.getName();
        this.description = category.getDescription();
        this.currency = category.getCurrency();
        this.faceValue = category.getFaceValue();
        this.interest = category.getInterest();
        this.fixedInterest = category.getFixedInterest();
    }
}
