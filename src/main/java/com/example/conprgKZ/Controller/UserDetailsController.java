package com.example.conprgKZ.Controller;


import com.example.conprgKZ.Service.UserDetailsService;
import com.example.conprgKZ.UserRegister.LoginRequest;
import com.example.conprgKZ.UserRegister.UserDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userService;

    @PostMapping("/register")
    public String Registration(@RequestBody UserDetailsRequest userDetailsRequest) {
        return userService.Register(userDetailsRequest);
    }

    @PostMapping("/login")
    public String Login(@RequestBody LoginRequest loginRequest) {
        return userService.Login(loginRequest);
    }

}

