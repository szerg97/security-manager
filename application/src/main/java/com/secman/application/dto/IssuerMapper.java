package com.secman.application.dto;

import com.secman.model.*;
import com.secman.service.AddressService;
import com.secman.service.CityService;
import com.secman.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IssuerMapper {

    private Address getAddress(Issuer issuer){
        return issuer.getAddress();
    }

    private City getCity(Address address){
        return address.getCity();
    }

    private Country getCountry(City city){
        return city.getCountry();
    }

    public IssuerDto fromEntity(Issuer from){
        Address address = getAddress(from);
        City city = getCity(address);
        Country country = getCountry(city);

        return new IssuerDto(
                from.getId(),
                from.getName(),
                from.getEmail(),
                from.getPhone(),
                from.getStatus(),
                address.getAddressLineTwo() != null ? address.getAddressLineOne() + " " + address.getAddressLineTwo() : address.getAddressLineOne(),
                city.getPostalCode() + " " + city.getName(),
                country.getName(),
                from.getOpeningHours().getWeekdays(),
                from.getOpeningHours().getSaturday(),
                from.getOpeningHours().getSunday()
        );
    }
}
