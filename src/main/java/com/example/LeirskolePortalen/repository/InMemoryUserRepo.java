package com.example.LeirskolePortalen.repository;


import org.springframework.security.core.userdetails.User;

import java.util.*;

public class InMemoryUserRepo {
    private static final Map<String, User> users = new HashMap<>();

    public static void save(User user) {
        users.put(user.getUsername(), user);
    }

    public static User findByEmail(String email) {
        return users.get(email);
    }
}
