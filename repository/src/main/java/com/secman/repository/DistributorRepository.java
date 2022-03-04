package com.secman.repository;

import com.secman.model.Address;
import com.secman.model.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Long> {
    List<Distributor> findByAddress(Address address);
}
