package com.firstcar.backend.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

     @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal priceEstimate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;

    @Column(precision = 5, scale = 2)
    private BigDecimal fuelEfficiency;

    private Integer reliabilityScore;

    private Integer insuranceGroup;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPriceEstimate() {
        return priceEstimate;
    }

    public void setPriceEstimate(BigDecimal priceEstimate) {
        this.priceEstimate = priceEstimate;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public BigDecimal getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelEfficiency(BigDecimal fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    public Integer getReliabilityScore() {
        return reliabilityScore;
    }

    public void setReliabilityScore(Integer reliabilityScore) {
        this.reliabilityScore = reliabilityScore;
    }

    public Integer getInsuranceGroup() {
        return insuranceGroup;
    }

    public void setInsuranceGroup(Integer insuranceGroup) {
        this.insuranceGroup = insuranceGroup;
    }

    


}
