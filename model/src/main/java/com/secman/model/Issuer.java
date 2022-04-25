package com.secman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "issuers")
@Getter
@Setter
@NoArgsConstructor
public class Issuer extends GSecEntity {

    @Schema(description = "Name of issuer")
    @NotBlank(message = "error.issuer.name.not-blank")
    @Size(min = 2, max = 128, message = "error.issuer.name.size")
    private String name;
    @Schema(description = "Email of issuer")
    @NotBlank(message = "error.issuer.email.not-blank")
    @Size(min = 5, max = 64, message = "error.issuer.email.size")
    private String email;
    @Schema(description = "Phone number of issuer")
    @NotBlank(message = "error.issuer.phone.not-blank")
    @Size(min = 2, max = 16, message = "error.issuer.phone.size")
    private String phone;
    @Schema(description = "Status of issuer (open/closed)")
    @NotBlank(message = "error.issuer.status.not-blank")
    private Boolean status;
    @Schema(description = "Address where the issuer takes place")
    @NotBlank(message = "error.issuer.address.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = Address.class)
    private Address address;
    @Schema(description = "Opening hours of the issuer")
    @NotBlank(message = "error.issuer.opening-hours.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = OpeningHours.class)
    private OpeningHours openingHours;

    @JsonIgnore
    @OneToMany(targetEntity = Transaction.class, mappedBy = "issuer")
    private List<Transaction> transactions;

    public Issuer(String name, String email, String phone, Boolean status, Address address, OpeningHours openingHours) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.address = address;
        this.openingHours = openingHours;
    }

    public void copyFrom(Issuer issuer) {
        this.name = issuer.getName();
        this.email = issuer.getEmail();
        this.phone = issuer.getPhone();
        this.status = issuer.getStatus();
        this.address = issuer.getAddress();
        this.openingHours = issuer.getOpeningHours();
    }
}
