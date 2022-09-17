package com.dev.globaldev.controller;

import com.dev.globaldev.entity.CvContent;
import com.dev.globaldev.entity.CvUsers;
import com.dev.globaldev.repository.CvContentRepository;
import com.dev.globaldev.repository.CvEducationRepository;
import com.dev.globaldev.repository.CvExperienceRepository;
import com.dev.globaldev.repository.CvUsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ContentController {

    private final CvUsersRepository cvUsersRepository;
    private final CvContentRepository cvContentRepository;
    private final CvEducationRepository cvEducationRepository;
    private final CvExperienceRepository cvExperienceRepository;

    Map<String, String> notFound = new HashMap<>();

    public ContentController(CvUsersRepository cvUsersRepository,
		         CvContentRepository cvContentRepository,
		         CvEducationRepository cvEducationRepository,
		         CvExperienceRepository cvExperienceRepository) {
        this.cvUsersRepository = cvUsersRepository;
        this.cvContentRepository = cvContentRepository;
        this.cvEducationRepository = cvEducationRepository;
        this.cvExperienceRepository = cvExperienceRepository;

        notFound.put("message", "not found");
    }

    @GetMapping("cv/id/{userID}")
    public ResponseEntity<Object> getAllCvOfUser(@PathVariable("userID") String userID) {
        CvUsers cvUsers = cvUsersRepository.findById(Long.parseLong(userID)).get();
        List<CvContent> contentList = cvContentRepository.findAllByCvUsers(cvUsers);
        return new ResponseEntity<>(contentList, HttpStatus.OK);
    }

    @PostMapping("cv/id/{userID}")
    public ResponseEntity<Object> createNewCv(@RequestBody Map<String, String> request, @PathVariable("userID") String userID) {
        CvUsers cvUsers = cvUsersRepository.findById(Long.parseLong(userID)).get();
        CvContent cvContent = new CvContent();
        cvContent.name = request.get("name");
        cvContent.linkedIn = request.get("linkedIn");
        cvContent.github = request.get("github");
        cvContent.mobile = request.get("mobile");
        cvContent.summary = request.get("summary");
        cvContent.skills = request.get("skills");
        cvContent.cvUsers = cvUsers;
        cvContentRepository.save(cvContent);
        return ResponseEntity.accepted().build();
    }


    @PostMapping("cv/id/{cvID}/update")
    public ResponseEntity<Object> updateCV(@RequestBody Map<String, String> request, @PathVariable String cvID) {
        CvContent cvContent = cvContentRepository.findById(Long.parseLong(cvID)).get();
        cvContent.name = request.get("name");
        cvContent.linkedIn = request.get("linkedIn");
        cvContent.github = request.get("github");
        cvContent.mobile = request.get("mobile");
        cvContent.summary = request.get("summary");
        cvContent.skills = request.get("skills");
        cvContentRepository.save(cvContent);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("cv/id/{cvID}/delete")
    public ResponseEntity<Object> deleteCV( @PathVariable String cvID) {
        cvContentRepository.deleteById(Long.parseLong(cvID));
        return ResponseEntity.accepted().build();
    }
}
