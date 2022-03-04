package com.secman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "securities")
@Getter
@Setter
@NoArgsConstructor
public class Security extends GSecEntity {

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
    @Schema(description = "Customer of security")
    @NotBlank(message = "error.security.customer.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    public Security(Double faceValue, Double denomination, Double grossValue, Double netValue, Double term, Double interest, Double accruedInterest, Boolean fixedInterest, Double yield, Double referenceYield, SecurityCategory category, Distributor distributor, Customer customer) {
        super();
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
        this.customer = customer;
    }

    public void copyFrom(Security security) {
        this.faceValue = security.getFaceValue();
        this.denomination = security.getDenomination();
        this.grossValue = security.getGrossValue();
        this.netValue = security.getNetValue();
        this.term = security.getTerm();
        this.interest = security.getInterest();
        this.accruedInterest = security.getAccruedInterest();
        this.fixedInterest = security.getFixedInterest();
        this.yield = security.getYield();
        this.referenceYield = security.getReferenceYield();
        this.category = security.getCategory();
        this.distributor = security.getDistributor();
        this.customer = security.getCustomer();
    }
}
