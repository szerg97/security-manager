package com.secman.application.dto;

import com.secman.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DemographyMapper {

    private int calculateAge(LocalDate date){
        return LocalDate.now().getYear() - date.getYear();
    }

    public DemographyDto fromEntities(List<Customer> from){

        return new DemographyDto(
            from.stream().filter(x -> calculateAge(x.getDateOfBirth()) < 25).collect(Collectors.toList()).size(),
            from.stream().filter(x -> calculateAge(x.getDateOfBirth()) >= 25
                    && calculateAge(x.getDateOfBirth()) < 30).collect(Collectors.toList()).size(),
            from.stream().filter(x -> calculateAge(x.getDateOfBirth()) >= 30
                    && calculateAge(x.getDateOfBirth()) < 40).collect(Collectors.toList()).size(),
            from.stream().filter(x -> calculateAge(x.getDateOfBirth()) >= 40
                    && calculateAge(x.getDateOfBirth()) < 60).collect(Collectors.toList()).size(),
            from.stream().filter(x -> calculateAge(x.getDateOfBirth()) >= 60).collect(Collectors.toList()).size()
        );
    }
}
