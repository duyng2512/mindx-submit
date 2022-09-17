package com.dev.globaldev.repository;

import com.dev.globaldev.entity.CvExperience;
import com.dev.globaldev.entity.CvUserSessions;
import com.dev.globaldev.entity.CvUsers;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CvExperienceRepository  extends PagingAndSortingRepository<CvExperience, Long> {
    List<CvExperience> findAllByCvUsers(CvUsers cvUsers);
}
