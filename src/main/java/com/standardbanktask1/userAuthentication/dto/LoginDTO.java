package com.standardbanktask1.userAuthentication.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LoginDTO {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void storeCredentials() throws IOException {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        BufferedWriter out = new BufferedWriter(new FileWriter("credentials.txt"));
        out.write(username + "#" + passwordEncoder.encode(password));
        out.newLine();
        out.close();
    }


}
