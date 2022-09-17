package com.dev.globaldev.controller;

import com.dev.globaldev.entity.CvContent;
import com.dev.globaldev.entity.CvEducation;
import com.dev.globaldev.entity.CvUsers;
import com.dev.globaldev.repository.CvContentRepository;
import com.dev.globaldev.repository.CvEducationRepository;
import com.dev.globaldev.repository.CvExperienceRepository;
import com.dev.globaldev.repository.CvUsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class EducationController {

    private final CvUsersRepository cvUsersRepository;
    private final CvEducationRepository cvEducationRepository;

    Map<String, String> notFound = new HashMap<>();

    public EducationController(CvUsersRepository cvUsersRepository,
                               CvEducationRepository cvEducationRepository) {
        this.cvUsersRepository = cvUsersRepository;
        this.cvEducationRepository = cvEducationRepository;

        notFound.put("message", "not found");
    }

    @GetMapping("edu/id/{userID}")
    public ResponseEntity<Object> getAllCvOfUser(@PathVariable("userID") String userID) {
        CvUsers cvUsers = cvUsersRepository.findById(Long.parseLong(userID)).get();
        List<CvEducation> contentList = cvEducationRepository.findAllByCvUsers(cvUsers);
        return new ResponseEntity<>(contentList, HttpStatus.OK);
    }

    @PostMapping("edu/id/{userID}")
    public ResponseEntity<Object> createNewCv(@RequestBody CvEducation cvEducation, @PathVariable("userID") String userID) {
        cvEducation.cvUsers = cvUsersRepository.findById(Long.parseLong(userID)).get();
        cvEducationRepository.save(cvEducation);
        return ResponseEntity.accepted().build();
    }

}
