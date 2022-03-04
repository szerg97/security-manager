package com.secman.service;

import com.secman.model.City;
import com.secman.model.Country;
import com.secman.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getAll(){
        return this.cityRepository.findAll();
    }

    public List<City> getByCountry(Country country){
        return this.cityRepository.findByCountry(country);
    }

    public City getOne(Long id){
        Optional<City> optional = this.cityRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public City addOne(City city){
        this.cityRepository.save(city);
        return city;
    }

    public City deleteOneById(Long id){
        City city = getOne(id);
        if (city != null){
            this.cityRepository.delete(city);
            return city;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public City updateOne(City pCity){
        City city = getOne(pCity.getId());
        if (city != null) {
            city.copyFrom(pCity);
            this.cityRepository.save(city);
            return city;
        }
        throw new RuntimeException("Could not update");
    }
}
