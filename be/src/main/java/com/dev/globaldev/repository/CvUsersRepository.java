package com.dev.globaldev.repository;

import com.dev.globaldev.entity.CvContent;
import com.dev.globaldev.entity.CvUsers;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvUsersRepository extends PagingAndSortingRepository<CvUsers, Long> {
    CvUsers findFirstByUserName(String userName);
}
