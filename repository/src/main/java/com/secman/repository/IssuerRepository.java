package com.secman.repository;

import com.secman.model.Address;
import com.secman.model.Issuer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuerRepository extends JpaRepository<Issuer, Long> {
    List<Issuer> findByAddress(Address address);
}
