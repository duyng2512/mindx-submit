package com.dev.globaldev.repository;

import com.dev.globaldev.entity.CvContent;
import com.dev.globaldev.entity.CvUserSessions;
import com.dev.globaldev.entity.CvUsers;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CvUsersSessionsRepository extends PagingAndSortingRepository<CvUserSessions, Long> {
    CvUserSessions findFirstByCvUsers(CvUsers cvUsers);
}
