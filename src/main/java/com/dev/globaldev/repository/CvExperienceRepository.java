package com.dev.globaldev.repository;

import com.dev.globaldev.entity.CvExperience;
import com.dev.globaldev.entity.CvUserSessions;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvExperienceRepository  extends PagingAndSortingRepository<CvExperience, Long> {
}
