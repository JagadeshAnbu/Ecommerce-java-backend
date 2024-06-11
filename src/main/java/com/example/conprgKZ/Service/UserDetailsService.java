package com.example.conprgKZ.Service;


import com.example.conprgKZ.Entity.UserDetails;
import com.example.conprgKZ.Repository.UserDetailsRepository;
import com.example.conprgKZ.UserRegister.LoginRequest;
import com.example.conprgKZ.UserRegister.UserDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserDetailsService {
    @Autowired
    UserDetailsRepository userRepository;

    public String Register(UserDetailsRequest userDetailssRequest) {
        String mailId = userDetailssRequest.getEmail();
        Optional<UserDetails> userDetailsOptional = userRepository.findByEmail(mailId);

        if (!userDetailsOptional.isPresent()) {
            UserDetails cd = new UserDetails();

            Pattern p = Pattern.compile("^[ A-Za-z]+$");
            Matcher m = p.matcher(userDetailssRequest.getUsername());
            boolean b = m.matches();
            if (userDetailssRequest.getUsername() != null && userDetailssRequest.getUsername().length() <= 25 && b &&
                    userDetailssRequest.getUsername().trim().length() > 0) {
                cd.setUsername(userDetailssRequest.getUsername());
            } else {
                return "Invalid Username";
            }
            if (userDetailssRequest.getPassword() != null && userDetailssRequest.getPassword().length() <= 15
                    && userDetailssRequest.getPassword().trim().length() > 0) {
                cd.setPassword(userDetailssRequest.getPassword());
            } else {
                return "Invalid password";
            }



            if (userDetailssRequest.getMobileNo() != null && userDetailssRequest.getMobileNo().length() <= 10 &&
                    userDetailssRequest.getMobileNo().length() >= 10) {
                cd.setMobileNo(userDetailssRequest.getMobileNo());
            } else {
                return "invalid Mobile Number";
            }
            Pattern p1 = Pattern.compile("^(?!.{21})[\\w]{5,}@([\\w]+\\.)+[\\w]+$", Pattern.CASE_INSENSITIVE);
            Matcher m1 = p1.matcher(userDetailssRequest.getEmail());
            boolean b1 = m1.matches();
            if ((userDetailssRequest.getEmail() != null && userDetailssRequest.getEmail().length() <= 20)
                    && (userDetailssRequest.getEmail().trim().length() > 0 && b1)) {
                cd.setEmail(userDetailssRequest.getEmail());
            } else {
                return "invalid email";
            }
            userRepository.save(cd);
            return "User Details Added Successfully";
        } else {
            return "This email id is already registered";
        }
    }
    public String Login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Optional<UserDetails> userDetailsOptional = userRepository.findByEmailAndPassword(email, password);
        if (userDetailsOptional.isPresent()) {
            return "Login Success";
        } else {
            return "Login Failed";
        }
    }

}
