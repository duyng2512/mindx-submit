package com.dev.globaldev.controller;

import com.dev.globaldev.entity.CvUserSessions;
import com.dev.globaldev.entity.CvUsers;
import com.dev.globaldev.helper.PasswordEncoder;
import com.dev.globaldev.repository.CvUsersRepository;
import com.dev.globaldev.repository.CvUsersSessionsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class AuthController {
    // DTO
    Map<String, String>  wrongPassword = new HashMap<>();
    Map<String, String>  logout = new HashMap<>();
    Map<String, String>  signup = new HashMap<>();


    private final CvUsersRepository cvUsersRepository;
    private final CvUsersSessionsRepository cvUsersSessionsRepository;

    public AuthController(CvUsersRepository cvUsersRepository, CvUsersSessionsRepository cvUsersSessionsRepository) {
        this.cvUsersRepository = cvUsersRepository;
        this.cvUsersSessionsRepository = cvUsersSessionsRepository;


        wrongPassword.put("message", "wrong password");
        logout.put("message", "logout successfully");
        signup.put("message", "signup successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> request) {
        String userName = request.get("userName");
        String password = request.get("password");

        CvUsers cvUsers = cvUsersRepository.findFirstByUserName(userName);

        // Check Password
        if (PasswordEncoder.validate(password, cvUsers.getPassword())) {
            CvUserSessions cvUserSessions = new CvUserSessions();
            cvUserSessions.setExpire(10000000000L);
            cvUserSessions.setCvUsers(cvUsers);
            cvUserSessions.setTokens("tokens_" + cvUsers.getId());
            cvUsersSessionsRepository.save(cvUserSessions);
            return new ResponseEntity<>(cvUsers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(wrongPassword, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@RequestBody Map<String, String> request) {
        String userName = request.get("userName");
        CvUsers cvUsers = cvUsersRepository.findFirstByUserName(userName);
        CvUserSessions cvUserSessions = cvUsersSessionsRepository.findFirstByCvUsers(cvUsers);
        cvUsersSessionsRepository.delete(cvUserSessions);
        return new ResponseEntity<>(logout, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody Map<String, String> request) {

        String userName = request.get("userName");
        String password = request.get("password");
        String role = request.get("role");

        CvUsers cvUsers = new CvUsers();
        cvUsers.setUserName(userName);
        cvUsers.setPassword(PasswordEncoder.encode(password));
        cvUsers.setRole(role);

        CvUsers savedCvUser = cvUsersRepository.save(cvUsers);

        CvUserSessions cvUserSessions = new CvUserSessions();
        cvUserSessions.setExpire(10000000000L);
        cvUserSessions.setCvUsers(savedCvUser);
        cvUserSessions.setTokens("tokens_" + savedCvUser.getId());
        cvUsersSessionsRepository.save(cvUserSessions);

        return new ResponseEntity<>(savedCvUser, HttpStatus.OK);
    }




}
