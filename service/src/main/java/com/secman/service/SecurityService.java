package com.secman.service;

import com.secman.model.Customer;
import com.secman.model.Distributor;
import com.secman.model.Security;
import com.secman.model.SecurityCategory;
import com.secman.repository.SecurityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityService {

    private final SecurityRepository securityRepository;

    public List<Security> getAll(){
        return this.securityRepository.findAll();
    }

    public List<Security> getByCustomer(Customer customer){
        return this.securityRepository.findByCustomer(customer);
    }

    public List<Security> getByDistributor(Distributor distributor){
        return this.securityRepository.findByDistributor(distributor);
    }

    public List<Security> getByCategory(SecurityCategory category){
        return this.securityRepository.findByCategory(category);
    }

    public Security getOne(Long id){
        Optional<Security> optional = this.securityRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public Security addOne(Security security){
        this.securityRepository.save(security);
        return security;
    }

    public Security deleteOneById(Long id){
        Security security = getOne(id);
        if (security != null){
            this.securityRepository.delete(security);
            return security;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public Security updateOne(Security pSecurity){
        Security security = getOne(pSecurity.getId());
        if (security != null) {
            security.copyFrom(pSecurity);
            this.securityRepository.save(security);
            return security;
        }
        throw new RuntimeException("Could not update");
    }
}
