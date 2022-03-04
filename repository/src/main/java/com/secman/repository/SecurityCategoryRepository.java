package com.secman.repository;

import com.secman.model.SecurityCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityCategoryRepository extends JpaRepository<SecurityCategory, Long> {
}
