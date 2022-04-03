package com.secman.service;

import com.secman.model.Portfolio;
import com.secman.repository.PortfolioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public List<Portfolio> getAll(){
        return this.portfolioRepository.findAll();
    }

    public Portfolio getByCustomer(String email){
        return this.portfolioRepository.findByCustomerEmail(email);
    }

    public Portfolio getOne(Long id){
        Optional<Portfolio> optional = this.portfolioRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public Portfolio addOne(Portfolio transaction){
        this.portfolioRepository.save(transaction);
        return transaction;
    }

    public Portfolio deleteOneById(Long id){
        Portfolio transaction = getOne(id);
        if (transaction != null){
            this.portfolioRepository.delete(transaction);
            return transaction;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public Portfolio updateOne(Portfolio pTransaction){
        Portfolio transaction = getOne(pTransaction.getId());
        if (transaction != null) {
            transaction.copyFrom(pTransaction);
            this.portfolioRepository.save(transaction);
            return transaction;
        }
        throw new RuntimeException("Could not update");
    }
}
