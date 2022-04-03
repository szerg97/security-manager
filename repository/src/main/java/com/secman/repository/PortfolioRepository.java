package com.secman.repository;

import com.secman.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Portfolio findByCustomerEmail(String email);
}
