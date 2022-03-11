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
@Table(name = "distributors")
@Getter
@Setter
@NoArgsConstructor
public class Distributor extends GSecEntity {

    @Schema(description = "Name of distributor")
    @NotBlank(message = "error.distributor.name.not-blank")
    @Size(min = 2, max = 128, message = "error.distributor.name.size")
    private String name;
    @Schema(description = "Email of distributor")
    @NotBlank(message = "error.distributor.email.not-blank")
    @Size(min = 5, max = 64, message = "error.distributor.email.size")
    private String email;
    @Schema(description = "Phone number of distributor")
    @NotBlank(message = "error.distributor.phone.not-blank")
    @Size(min = 2, max = 16, message = "error.distributor.phone.size")
    private String phone;
    @Schema(description = "Status of distributor (open/closed)")
    @NotBlank(message = "error.distributor.status.not-blank")
    private Boolean status;
    @Schema(description = "Address where the distributor takes place")
    @NotBlank(message = "error.distributor.address.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = Address.class)
    private Address address;

    @JsonIgnore
    @OneToMany(targetEntity = Transaction.class, mappedBy = "distributor")
    private List<Transaction> securities;

    public Distributor(String name, String email, String phone, Boolean status, Address address) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.address = address;
    }

    public void copyFrom(Distributor distributor) {
        this.name = distributor.getName();
        this.email = distributor.getEmail();
        this.phone = distributor.getPhone();
        this.status = distributor.getStatus();
        this.address = distributor.getAddress();
    }
}
