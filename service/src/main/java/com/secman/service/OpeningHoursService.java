package com.secman.service;

import com.secman.model.Country;
import com.secman.model.OpeningHours;
import com.secman.repository.OpeningHoursRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OpeningHoursService {

    private final OpeningHoursRepository openingHoursRepository;

    public List<OpeningHours> getAll(){
        return this.openingHoursRepository.findAll();
    }

    public OpeningHours getOne(Long id){
        Optional<OpeningHours> optional = this.openingHoursRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public OpeningHours addOne(OpeningHours openingHours) throws Exception{
        this.openingHoursRepository.save(openingHours);
        return openingHours;
    }

    public OpeningHours deleteOneById(Long id){
        OpeningHours openingHours = getOne(id);
        if (openingHours != null){
            this.openingHoursRepository.delete(openingHours);
            return openingHours;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public OpeningHours updateOne(OpeningHours hours){
        OpeningHours openingHours = getOne(hours.getId());
        if (openingHours != null) {
            openingHours.copyFrom(hours);
            this.openingHoursRepository.save(openingHours);
            return openingHours;
        }
        throw new RuntimeException("Could not update");
    }
}
