package com.secman.service;

import com.secman.model.Address;
import com.secman.model.Issuer;
import com.secman.repository.IssuerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IssuerService {

    private final IssuerRepository issuerRepository;

    public List<Issuer> getAll(){
        return this.issuerRepository.findAll();
    }

    public List<Issuer> getByAddress(Address address){
        return this.issuerRepository.findByAddress(address);
    }

    public Issuer getOne(Long id){
        Optional<Issuer> optional = this.issuerRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public Issuer addOne(Issuer issuer){
        this.issuerRepository.save(issuer);
        return issuer;
    }

    public Issuer deleteOneById(Long id){
        Issuer issuer = getOne(id);
        if (issuer != null){
            this.issuerRepository.delete(issuer);
            return issuer;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public Issuer updateOne(Issuer pIssuer){
        Issuer issuer = getOne(pIssuer.getId());
        if (issuer != null) {
            issuer.copyFrom(pIssuer);
            this.issuerRepository.save(issuer);
            return issuer;
        }
        throw new RuntimeException("Could not update");
    }
}
