package com.dev.globaldev.repository;

import com.dev.globaldev.entity.CvContent;
import com.dev.globaldev.entity.CvUsers;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CvContentRepository extends PagingAndSortingRepository<CvContent, Long> {
    List<CvContent> findAllByCvUsers(CvUsers cvUsers);
}
