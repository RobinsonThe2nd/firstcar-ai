package com.firstcar.backend.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstcar.backend.entity.FinancialProfile;
import com.firstcar.backend.service.FinancialProfileService;

@RestController
@RequestMapping("/api/users")
public class FinancialProfileController {
    
    private final FinancialProfileService financialProfileService;

    //constructor
    @Autowired
    public FinancialProfileController(FinancialProfileService financialProfileService){
        this.financialProfileService = financialProfileService;
    }

    @PutMapping("/{userId}/financial-profile")
    public ResponseEntity<?> saveOrUpdateProfile(@PathVariable Long userId, @RequestBody FinancialProfileRequest request){

        try {
            FinancialProfile profile = financialProfileService.saveOrUpdateProfile(
                userId,
                request.getNetPay(),
                request.getMonthlyExpenses(),
                request.getLicenseYears()
            );
            return ResponseEntity.ok(profile);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

        //inner class representing the expecting JSON format
        public static class FinancialProfileRequest{

            private BigDecimal netPay;
            private BigDecimal monthlyExpenses;
            private Integer licenseYears;
            public BigDecimal getNetPay() {
                return netPay;
            }
            public void setNetPay(BigDecimal netPay) {
                this.netPay = netPay;
            }
            public BigDecimal getMonthlyExpenses() {
                return monthlyExpenses;
            }
            public void setMonthlyExpenses(BigDecimal monthlyExpenses) {
                this.monthlyExpenses = monthlyExpenses;
            }
            public Integer getLicenseYears() {
                return licenseYears;
            }
            public void setLicenseYears(Integer licenseYears) {
                this.licenseYears = licenseYears;
            }

        }
    


}
