package com.secman.application.dto;

import com.secman.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerMapper {

    private Address getAddress(Customer customer){
        return customer.getAddress();
    }

    private City getCity(Address address){
        return address.getCity();
    }

    private Country getCountry(City city){
        return city.getCountry();
    }

    public CustomerDto fromEntity(Customer from, PortfolioDto portfolioDto){
        Address address = getAddress(from);
        City city = getCity(address);
        Country country = getCountry(city);

        return new CustomerDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getEmail(),
                from.getPhone(),
                from.getIdCard(),
                from.getGender(),
                from.getDateOfBirth(),
                from.getInserted(),
                address.getAddressLineTwo() != null ? address.getAddressLineOne() + " " + address.getAddressLineTwo() : address.getAddressLineOne(),
                city.getPostalCode() + " " + city.getName(),
                country.getName(),
                portfolioDto
        );
    }
}
