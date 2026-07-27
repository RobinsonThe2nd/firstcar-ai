package com.firstcar.backend.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstcar.backend.entity.Car;
import com.firstcar.backend.entity.FinancialProfile;
import com.firstcar.backend.entity.User;
import com.firstcar.backend.repository.FinancialProfileRepository;
import com.firstcar.backend.repository.UserRepository;
import com.firstcar.backend.service.ScoringService;


@RestController
@RequestMapping("/api/users")
public class RecommendationController {
    
    private final FinancialProfileRepository financialProfileRepository;
    private final UserRepository userRepository;
    private final ScoringService scoringService;

    public RecommendationController(FinancialProfileRepository financialProfileRepository,
            UserRepository userRepository, ScoringService scoringService) {
        this.financialProfileRepository = financialProfileRepository;
        this.userRepository = userRepository;
        this.scoringService = scoringService;
    }

    @GetMapping("/{userId}/recommendations")
    public ResponseEntity<?> getRecommendations(@PathVariable Long userId){
        try {

            //get user
            User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found."));

            //get users financial profile
            FinancialProfile profile = financialProfileRepository.findByUser(user).orElseThrow(() -> new IllegalArgumentException("Financial profile not found for this user."));

            //get the cars according to profile
            List<Car> recommendations = scoringService.getTopMatches(profile, 5);

            //return the recommendations
            return ResponseEntity.ok(recommendations);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    

}
