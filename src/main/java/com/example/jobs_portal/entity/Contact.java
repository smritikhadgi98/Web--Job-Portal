package com.example.jobs_portal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="contact")

public class Contact{
    @Id
    @SequenceGenerator(name = "jps_contact_seq_gen", sequenceName = "jps_contact_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "jps_contact_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "firstname",nullable = false)
    private String firstname;
    @Column(name = "lastname",nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String email;

    @Column(name = "mobile_no",nullable = false)
    private String mobile_no;

    @Column(name = "message",nullable = false)
    private String message;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
