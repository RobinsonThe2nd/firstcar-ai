package com.firstcar.backend.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstcar.backend.entity.Car;
import com.firstcar.backend.entity.FinancialProfile;
import com.firstcar.backend.repository.CarRepository;

@Service
public class ScoringService {
    
    private final CarRepository carRepository;

    @Autowired
    public ScoringService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<Car> getTopMatches(FinancialProfile profile, int limit){

        BigDecimal disposableIncome = profile.getNetPay().subtract((profile.getMonthlyExpenses()));

        List<Car> cars = carRepository.findAll();

        return cars.stream()
        .sorted(Comparator.comparingDouble(
                (Car car) -> calculateTotalScore(car, disposableIncome)
            ).reversed())
            .limit(limit)
            .collect(Collectors.toList());


    }

    private double calculateTotalScore(Car car, BigDecimal disposableIncome){
        double affordability = calculateAffordabilityScore(car, disposableIncome);
        double reliability = car.getReliabilityScore();
        double insurance = 11 - car.getInsuranceGroup();
        double efficiency = calculateEfficiencyScore(car);

        return (affordability * 0.4)
                + (reliability * 0.3)
                + (insurance * 0.15)
                + (efficiency * 0.15);
    }

    private double calculateAffordabilityScore(Car car, BigDecimal disposableIncome){

        if (disposableIncome.compareTo(BigDecimal.ZERO) <= 0) {
            return 0;            
        }

        BigDecimal monthlyIsntallment = car.getPriceEstimate().divide(BigDecimal.valueOf(72), 2, RoundingMode.HALF_UP);

        double ratio = monthlyIsntallment.doubleValue() / disposableIncome.doubleValue();

        double idealRatio = 0.30;
        double score = 10 - (ratio / idealRatio) * 10;

        if (score < 0) score = 0;
        if (score > 10) score = 10;

        return score;

    }

    private double calculateEfficiencyScore(Car car){

        double efficiency = car.getFuelEfficiency().doubleValue();
        double score = 10 - ((efficiency - 4.0) / (10.0 - 4.0)) * 10;
        if (score < 0) score = 0;
        if (score > 10) score = 10;
        return score;

    }


}
