package com.secman.service;

import com.secman.model.Country;
import com.secman.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryService{

    private final CountryRepository countryRepository;

    public List<Country> getAll(){
        return this.countryRepository.findAll();
    }

    public Country getOne(Long id){
        Optional<Country> optional = this.countryRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public Country addOne(Country country) throws Exception{
        this.countryRepository.save(country);
        return country;
    }

    public Country deleteOneById(Long id){
        Country country = getOne(id);
        if (country != null){
            this.countryRepository.delete(country);
            return country;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public Country updateOne(Country pCountry){
        Country country = getOne(pCountry.getId());
        if (country != null) {
            country.copyFrom(pCountry);
            this.countryRepository.save(country);
            return country;
        }
        throw new RuntimeException("Could not update");
    }
}
