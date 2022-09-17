package com.dev.globaldev.repository;

import com.dev.globaldev.entity.CvEducation;
import com.dev.globaldev.entity.CvExperience;
import com.dev.globaldev.entity.CvUsers;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CvEducationRepository extends PagingAndSortingRepository<CvEducation, Long> {
    List<CvEducation> findAllByCvUsers(CvUsers cvUsers);
}
