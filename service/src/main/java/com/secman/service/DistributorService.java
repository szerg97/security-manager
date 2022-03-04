package com.secman.service;

import com.secman.model.Address;
import com.secman.model.Distributor;
import com.secman.repository.DistributorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DistributorService {

    private final DistributorRepository distributorRepository;

    public List<Distributor> getAll(){
        return this.distributorRepository.findAll();
    }

    public List<Distributor> getByAddress(Address address){
        return this.distributorRepository.findByAddress(address);
    }

    public Distributor getOne(Long id){
        Optional<Distributor> optional = this.distributorRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public Distributor addOne(Distributor distributor){
        this.distributorRepository.save(distributor);
        return distributor;
    }

    public Distributor deleteOneById(Long id){
        Distributor distributor = getOne(id);
        if (distributor != null){
            this.distributorRepository.delete(distributor);
            return distributor;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public Distributor updateOne(Distributor pDistributor){
        Distributor distributor = getOne(pDistributor.getId());
        if (distributor != null) {
            distributor.copyFrom(pDistributor);
            this.distributorRepository.save(distributor);
            return distributor;
        }
        throw new RuntimeException("Could not update");
    }
}
