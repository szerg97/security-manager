package com.secman.service;

import com.secman.model.Issuer;
import com.secman.model.Portfolio;
import com.secman.model.Transaction;
import com.secman.model.Security;
import com.secman.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public List<Transaction> getAll(){
        return this.transactionRepository.findAll();
    }

    public List<Transaction> getByPortfolio(Portfolio portfolio){
        return this.transactionRepository.findByPortfolio(portfolio);
    }

    public List<Transaction> getByIssuer(Issuer issuer){
        return this.transactionRepository.findByIssuer(issuer);
    }

    public List<Transaction> getBySecurity(Security category){
        return this.transactionRepository.findBySecurity(category);
    }

    public Transaction getOne(Long id){
        Optional<Transaction> optional = this.transactionRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public Transaction addOne(Transaction transaction){
        this.transactionRepository.save(transaction);
        return transaction;
    }

    public Transaction deleteOneById(Long id){
        Transaction transaction = getOne(id);
        if (transaction != null){
            this.transactionRepository.delete(transaction);
            return transaction;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public Transaction updateOne(Transaction pTransaction){
        Transaction transaction = getOne(pTransaction.getId());
        if (transaction != null) {
            transaction.copyFrom(pTransaction);
            this.transactionRepository.save(transaction);
            return transaction;
        }
        throw new RuntimeException("Could not update");
    }
}
