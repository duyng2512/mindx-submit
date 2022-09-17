package com.dev.globaldev.controller;

import com.dev.globaldev.entity.CvEducation;
import com.dev.globaldev.entity.CvExperience;
import com.dev.globaldev.entity.CvUsers;
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
public class ExperienceController {

    private final CvUsersRepository cvUsersRepository;
    private final CvExperienceRepository cvExperienceRepository;

    Map<String, String> notFound = new HashMap<>();

    public ExperienceController(CvUsersRepository cvUsersRepository,
                                CvExperienceRepository cvExperienceRepository) {
        this.cvUsersRepository = cvUsersRepository;
        this.cvExperienceRepository = cvExperienceRepository;

        notFound.put("message", "not found");
    }

    @GetMapping("exp/id/{userID}")
    public ResponseEntity<Object> getAllCvOfUser(@PathVariable("userID") String userID) {
        CvUsers cvUsers = cvUsersRepository.findById(Long.parseLong(userID)).get();
        List<CvExperience> contentList = cvExperienceRepository.findAllByCvUsers(cvUsers);
        return new ResponseEntity<>(contentList, HttpStatus.OK);
    }

    @PostMapping("exp/id/{userID}")
    public ResponseEntity<Object> createNewCv(@RequestBody CvExperience cvExperience, @PathVariable("userID") String userID) {
        cvExperience.cvUsers = cvUsersRepository.findById(Long.parseLong(userID)).get();
        cvExperienceRepository.save(cvExperience);
        return ResponseEntity.accepted().build();
    }

}
