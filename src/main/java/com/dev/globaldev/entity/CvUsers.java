package com.dev.globaldev.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter @Setter
public class CvUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    String userName;

    String password;

    String role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cvUsers")
    Set<CvExperience> experiences = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cvUsers")
    Set<CvEducation> educations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
