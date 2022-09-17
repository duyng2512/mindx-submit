package com.dev.globaldev.config;

import com.dev.globaldev.entity.CvContent;
import com.dev.globaldev.entity.CvEducation;
import com.dev.globaldev.entity.CvExperience;
import com.dev.globaldev.entity.CvUsers;
import com.dev.globaldev.helper.PasswordEncoder;
import com.dev.globaldev.repository.CvContentRepository;
import com.dev.globaldev.repository.CvEducationRepository;
import com.dev.globaldev.repository.CvExperienceRepository;
import com.dev.globaldev.repository.CvUsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeDatabase implements CommandLineRunner {


    private final CvUsersRepository cvUsersRepository;
    private final CvContentRepository cvContentRepository;
    private final CvEducationRepository cvEducationRepository;
    private final CvExperienceRepository cvExperienceRepository;

    public InitializeDatabase(CvUsersRepository cvUsersRepository,
                              CvContentRepository cvContentRepository,
                              CvEducationRepository cvEducationRepository,
                              CvExperienceRepository cvExperienceRepository) {
        this.cvUsersRepository = cvUsersRepository;
        this.cvContentRepository = cvContentRepository;
        this.cvEducationRepository = cvEducationRepository;
        this.cvExperienceRepository = cvExperienceRepository;
    }

    /**
     * Init Some User
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        // Data
        CvUsers cvUsers = new CvUsers();
        cvUsers.setUserName("user");
        cvUsers.setPassword(PasswordEncoder.encode("user"));
        cvUsers.setRole("user");

        CvUsers cvAdmin = new CvUsers();
        cvAdmin.setUserName("admin");
        cvAdmin.setPassword(PasswordEncoder.encode("admin"));
        cvAdmin.setRole("admin");

        CvUsers savedUser = cvUsersRepository.save(cvUsers);

        CvContent cvContent = new CvContent();
        cvContent.email = "duyng2512@gmail.com";
        cvContent.linkedIn = "https://www.linkedin.com/feed/";
        cvContent.github = "https://gitlab.zalopay.vn/users/sign_in";
        cvContent.mobile = "0798030258";
        cvContent.summary = "this is summary";
        cvContent.skills = "java,react,html";
        cvContent.cvUsers = savedUser;
        cvContent.name = "Duy Ng";

        CvEducation cvEducation = new CvEducation();
        cvEducation.university = "HCMUT";
        cvEducation.faculty = "Info Tech";
        cvEducation.gpa = "8.0";
        cvEducation.cvUsers = savedUser;

        CvExperience cvExperience = new CvExperience();
        cvExperience.company = "Zalo";
        cvExperience.description = "Risk Team";
        cvExperience.period = "2020 - 2022";
        cvExperience.title = "SW Engineer";
        cvExperience.cvUsers = savedUser;


        cvUsersRepository.save(cvAdmin);
        cvContentRepository.save(cvContent);
        cvExperienceRepository.save(cvExperience);
        cvEducationRepository.save(cvEducation);

    }
}
