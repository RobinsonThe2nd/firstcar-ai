package com.firstcar.backend.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.firstcar.backend.repository.FinancialProfileRepository;
import com.firstcar.backend.repository.UserRepository;
import com.firstcar.backend.entity.FinancialProfile;
import com.firstcar.backend.entity.User;

@Service
public class FinancialProfileService {
    
    private final FinancialProfileRepository financialProfileRepository;
    private final UserRepository userRepository;

    //constructor
    public FinancialProfileService(FinancialProfileRepository financialProfileRepository, UserRepository userRepository){
        this.financialProfileRepository = financialProfileRepository;
        this.userRepository = userRepository;
    }

    public FinancialProfile saveOrUpdateProfile(Long userId, BigDecimal netPay, BigDecimal monthlyExpenses, Integer licenseYears){

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found."));

        FinancialProfile profile = financialProfileRepository.findByUser(user).orElseGet(() -> {

            FinancialProfile newProfile = new FinancialProfile();
            newProfile.setUser(user);
            return newProfile;

        });

        profile.setNetPay(netPay);
        profile.setMonthlyExpenses(monthlyExpenses);
        profile.setLicenseYears(licenseYears);
        return financialProfileRepository.save(profile);

    }

}
