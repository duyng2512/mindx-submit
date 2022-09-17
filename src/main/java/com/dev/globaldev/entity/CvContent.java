package com.dev.globaldev.entity;

import javax.persistence.*;

@Entity
public class CvContent {

    /*
        Information: Name, email, mobile, github and LinkedIn.
        Summary: short description about yourself.
        Experiences: job title, company, period and job description.
        Education: university/college, faculty, GPA.
        Skills: whatever you want.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    public CvUsers cvUsers;

    public Long getId() {
        return id;
    }

    public String name;

    public String email;

    public String mobile;

    public String github;

    public String linkedIn;

    public String summary;

    public String skills;


    public void setId(Long id) {
        this.id = id;
    }
}
