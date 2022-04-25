package com.secman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "opening_hours")
@Getter
@Setter
@NoArgsConstructor
public class OpeningHours extends GSecEntity{

    @Schema(description = "Weekdays")
    @NotBlank(message = "error.opening-hours.weekdays.not-blank")
    private String weekdays;
    @Schema(description = "Saturday")
    @NotBlank(message = "error.opening-hours.saturday.not-blank")
    private String saturday;
    @Schema(description = "Sunday")
    @NotBlank(message = "error.opening-hours.sunday.not-blank")
    private String sunday;

    @JsonIgnore
    @OneToMany(targetEntity = Issuer.class, mappedBy = "openingHours")
    private List<Issuer> issuers;

    public OpeningHours(String weekdays, String saturday, String sunday) {
        this.weekdays = weekdays;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public String getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(String weekdays) {
        this.weekdays = weekdays;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public void copyFrom(OpeningHours hours) {
        this.weekdays = hours.getWeekdays();
        this.saturday = hours.getSaturday();
        this.sunday = hours.getSunday();
    }
}
