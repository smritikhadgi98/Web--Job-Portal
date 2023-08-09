package com.example.jobs_portal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="jobs")
public class Jobs {
    @Id
    @SequenceGenerator(name = "jps_jobs_seq_gen", sequenceName = "jps_jobs_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "jps_jobs_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "job_title",nullable = false)
    private String jobTitle;

    @Column(name = "time-description",nullable = false)
    private String time_description;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "category",nullable = false)
    private String category;

    @Column(name = "full_description",nullable = false)
    private String full_description;

    @Column(name = "salary",nullable = false)
    private String salary;


    @Column(name = "contact",nullable = false)
    private String contact;

    @Column(name = "skill_1",nullable = false)
    private String skill1;
    @Column(name = "skill_2",nullable = false)
    private String skill2;
    @Column(name = "skill_3",nullable = false)
    private String skill3;
    @Column(name = "skill_4",nullable = false)
    private String skill4;
    @Column(name = "education",nullable = false)
    private String education;
    @Column(name = "qualification1",nullable = false)
    private String qualification1;
    @Column(name = "qualification2",nullable = false)
    private String qualification2;
    @Column(name = "qualification3",nullable = false)
    private String qualification3;
    @Column(name = "Experience",nullable = false)
    private String Experience1;

    @Column(name = "Experience2",nullable = false)
    private String Experience2;

    @Column(name = "image")
    private String image;

    @Transient
    private String imageBase64;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
