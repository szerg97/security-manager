package com.secman.repository;

import com.secman.model.Customer;
import com.secman.model.Distributor;
import com.secman.model.Security;
import com.secman.model.SecurityCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityRepository extends JpaRepository<Security, Long> {
    List<Security> findByCustomer(Customer customer);
    List<Security> findByCategory(SecurityCategory category);
    List<Security> findByDistributor(Distributor distributor);
}
