package com.secman.repository;

import com.secman.model.Issuer;
import com.secman.model.Portfolio;
import com.secman.model.Transaction;
import com.secman.model.SecurityCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByPortfolio(Portfolio portfolio);
    List<Transaction> findByCategory(SecurityCategory category);
    List<Transaction> findByIssuer(Issuer issuer);
}
