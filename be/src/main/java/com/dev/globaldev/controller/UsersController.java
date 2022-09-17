package com.dev.globaldev.controller;

import com.dev.globaldev.entity.CvUserSessions;
import com.dev.globaldev.entity.CvUsers;
import com.dev.globaldev.helper.PasswordEncoder;
import com.dev.globaldev.repository.CvUsersRepository;
import com.dev.globaldev.repository.CvUsersSessionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UsersController {
    // DTO
    Map<String, String>  wrongPassword = new HashMap<>();
    Map<String, String>  logout = new HashMap<>();
    Map<String, String>  signup = new HashMap<>();


    private final CvUsersRepository cvUsersRepository;
    private final CvUsersSessionsRepository cvUsersSessionsRepository;

    public UsersController(CvUsersRepository cvUsersRepository, CvUsersSessionsRepository cvUsersSessionsRepository) {
        this.cvUsersRepository = cvUsersRepository;
        this.cvUsersSessionsRepository = cvUsersSessionsRepository;


        wrongPassword.put("message", "wrong password");
        logout.put("message", "logout successfully");
        signup.put("message", "signup successfully");
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(cvUsersRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUseInfo(@PathVariable String id) {
        return new ResponseEntity<>(cvUsersRepository.findById(Long.parseLong(id)), HttpStatus.OK);
    }
}
