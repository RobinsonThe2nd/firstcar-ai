package com.firstcar.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstcar.backend.entity.FinancialProfile;
import com.firstcar.backend.entity.User;

public interface FinancialProfileRepository extends JpaRepository<FinancialProfile, Long>{
    
    Optional<FinancialProfile> findByUser(User user);

    boolean existsByUser(User user);

}
