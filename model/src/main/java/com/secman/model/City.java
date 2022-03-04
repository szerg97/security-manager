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
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
public class City extends GSecEntity{

    @Schema(description = "Name of city")
    @NotBlank(message = "error.city.name.not-blank")
    @Size(min = 2, max = 128, message = "error.city.name.size")
    private String name;
    @Schema(description = "Postal code of city")
    @NotBlank(message = "error.city.postal-code.not-blank")
    @Size(min = 2, max = 16, message = "error.city.postal-code.size")
    private String postalCode;
    @Schema(description = "State of city")
    @NotBlank(message = "error.city.state.not-blank")
    @Size(min = 2, max = 128, message = "error.city.state.size")
    private String state;
    @Schema(description = "Country where the city takes place")
    @NotBlank(message = "error.city.country.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = Country.class)
    private Country country;

    @JsonIgnore
    @OneToMany(targetEntity = Address.class, mappedBy = "city")
    private List<Address> addresses;

    public City(String name, String postalCode, String state, Country country) {
        super();
        this.name = name;
        this.postalCode = postalCode;
        this.state = state;
        this.country = country;
    }

    public void copyFrom(City city){
        this.name = city.getName();
        this.postalCode = city.getPostalCode();
        this.state = city.getState();
        this.country = city.getCountry();
    }
}
