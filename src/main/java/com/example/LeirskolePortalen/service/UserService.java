// src/main/java/com/example/LeirskolePortalen/service/UserService.java
package com.example.LeirskolePortalen.service;

import com.example.LeirskolePortalen.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public boolean register(User user) {
        if (users.containsKey(user.getEmail())) return false;
        users.put(user.getEmail(), user);
        return true;
    }

    public boolean login(String email, String password) {
        User user = users.get(email);
        return user != null && user.getPassword().equals(password);
    }
}
