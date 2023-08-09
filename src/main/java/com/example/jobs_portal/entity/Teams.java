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
@Table(name="teams")
public class Teams {
    @Id
    @SequenceGenerator(name = "jps_teams_seq_gen", sequenceName = "jps_teams_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "jps_teams_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "fullname",nullable = false)
    private String fullname;

    @Column(name = "Address",nullable = false)
    private String address;

    @Column(name = "contact",nullable = false)
    private String contact;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "department",nullable = false)
    private String department;


    @Column(name = "facebook",nullable = false)
    private String facebook;

    @Column(name = "linkedin",nullable = false)
    private String linkedin;

    @Column(name = "Github",nullable = false)
    private String github;

    @Column(name = "image")
    private String image;

    @Transient
    private String imageBase64;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


}
