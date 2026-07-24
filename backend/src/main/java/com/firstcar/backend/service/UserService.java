package com.firstcar.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.firstcar.backend.entity.User;
import com.firstcar.backend.repository.UserRepository;
import com.firstcar.backend.util.SAIdNumberUtil;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registerUser(User user){

        //Check if ID number is valid.
        if (SAIdNumberUtil.isValid(user.getIdNumber())) {
            throw new IllegalArgumentException("Invalid South African ID number.");
        }

        //Check if email and ID number is already registered.
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        if (userRepository.existsByIdNumber(user.getIdNumber())) {
            throw new IllegalArgumentException("ID number already registered.");
        }

        //set gender using extraction method from the SAIdNUmberUtil class
        user.setGender(SAIdNumberUtil.extractGender(user.getIdNumber()));

        //set date of birth using extraction method from the SAIdNUmberUtil class
        user.setDateOfBirth(SAIdNumberUtil.extractDateOfBirth(user.getIdNumber()));

        return userRepository.save(user);

    }


}
