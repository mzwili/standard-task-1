package com.standardbanktask1.userAuthentication.controller;

import com.standardbanktask1.userAuthentication.dto.LoginDTO;
import com.standardbanktask1.userAuthentication.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private String jwtToken;


    @PostMapping("/authenticate")
    public String userLogin(@RequestBody LoginDTO loginDTO){
        try {
            loginDTO.storeCredentials();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
             jwtToken = jwtService.generateToken(loginDTO.getUsername());
            if(authentication.isAuthenticated()){
                return jwtToken;
            }
        }catch (Exception e){
            System.out.println("An error occurred while processing your request: " + e.getMessage());
        }
        return jwtToken;
    }
}
