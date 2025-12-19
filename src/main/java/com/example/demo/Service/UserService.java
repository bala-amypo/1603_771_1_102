// src/main/java/com/example/demo/service/UserService.java
package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {
    User register(User user);
    User findByEmail(String email);
    List<User> getAllUsers();
}
