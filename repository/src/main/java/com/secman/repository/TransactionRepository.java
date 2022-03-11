package com.secman.repository;

import com.secman.model.Distributor;
import com.secman.model.Transaction;
import com.secman.model.SecurityCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
//    List<Transaction> findByCustomerEmail(String email);
    List<Transaction> findByCategory(SecurityCategory category);
    List<Transaction> findByDistributor(Distributor distributor);
}
