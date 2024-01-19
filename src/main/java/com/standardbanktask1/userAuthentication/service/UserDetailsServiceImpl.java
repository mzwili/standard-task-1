package com.standardbanktask1.userAuthentication.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private PasswordEncoder encryptPassword;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        encryptPassword = new BCryptPasswordEncoder();
        Map<String,String> authenticationDB = new HashMap<>();
        authenticationDB.put("thuthu", encryptPassword.encode("standard"));

        if(authenticationDB.containsKey(username)){
            return new User(username, authenticationDB.get(username), new ArrayList<>());
        }else {
            throw new UsernameNotFoundException(username);
        }
    };
}
