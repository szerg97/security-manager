package com.secman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
public class Country extends GSecEntity {

    @Schema(description = "Code of country")
    @NotBlank(message = "error.country.country-code.not-blank")
    @Size(min = 2, max = 2, message = "error.country.country-code.size")
    private String countryCode;
    @Schema(description = "Name of country")
    @NotBlank(message = "error.country.name.not-blank")
    @Size(min = 2, max = 128, message = "error.country.name.size")
    private String name;

    @JsonIgnore(value = true)
    @OneToMany(targetEntity = City.class, mappedBy = "country")
    private List<City> cities;

    public Country(String name, String countryCode) {
        super();
        this.name = name;
        this.countryCode = countryCode;
    }

    public void copyFrom(Country country){
        this.countryCode = country.getCountryCode();
        this.name = country.getName();
    }
}
