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
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
public class Address extends GSecEntity{

    @Schema(description = "Address line 1")
    @NotBlank(message = "error.address.address-line-one.not-blank")
    @Size(min = 2, max = 128, message = "error.address.address-line-one.size")
    private String addressLineOne;
    @Schema(description = "Address line 2")
    @Size(min = 2, max = 128, message = "error.address.address-line-one.size")
    private String addressLineTwo;
    @Schema(description = "City where the address takes place")
    @NotBlank(message = "error.address.city.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = City.class)
    private City city;

    @JsonIgnore
    @OneToMany(targetEntity = Issuer.class, mappedBy = "address")
    private List<Issuer> issuers;
    @JsonIgnore
    @OneToMany(targetEntity = Customer.class, mappedBy = "address")
    private List<Customer> customers;

    public Address(String addressLineOne, String addressLineTwo, City city) {
        super();
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
    }

    public void copyFrom(Address address) {
        this.addressLineOne = address.getAddressLineOne();
        this.addressLineTwo = address.getAddressLineTwo();
        this.city = address.getCity();
    }
}
