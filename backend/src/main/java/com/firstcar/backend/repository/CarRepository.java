package com.firstcar.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.firstcar.backend.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long>{
    
}
