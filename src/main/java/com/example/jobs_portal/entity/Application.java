package com.example.jobs_portal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.Collection;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="application")
public class Application {
    @Id
    @SequenceGenerator(name = "jps_app_seq_gen", sequenceName = "jps_app_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "jps_app_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;


    @Column(name = "gender",nullable = false)
    private String gender;

    @Column(name = "address",nullable = false)
    private String address;

    @Column(name = "file")
    private String file;

    @Column(name = "id_type",nullable = false)
    private String id_type;

    @Column(name = "id_num",nullable = false)
    private String id_num;

//    @Column(name = "status")
//    private String status;


    @Transient
    private String resume;

    @ManyToOne
    @JoinColumn(name = "Applied_jobs_FK", referencedColumnName = "id")
    private Jobs jobs_id;
    @ManyToOne
    @JoinColumn(name = "Applied_User", referencedColumnName = "id")
    private User user_id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}

