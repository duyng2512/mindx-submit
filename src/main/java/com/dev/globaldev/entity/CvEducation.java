package com.dev.globaldev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class CvEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIgnore
    public CvUsers cvUsers;

    public String university;
    public String faculty;
    public String gpa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
