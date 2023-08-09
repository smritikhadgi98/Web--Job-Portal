package com.example.jobs_portal.pojo;

import com.example.jobs_portal.entity.Teams;
import com.example.jobs_portal.entity.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamsPojo {
    private Integer id;
    @NotEmpty(message = "email cannot be empty")
    private String email;
    @NotEmpty(message = "contact cannot be empty")
    private  String contact;
    @NotEmpty(message = "fullname cannot be empty")
    private  String fullname;
    @NotEmpty(message = "address cannot be empty")
    private  String address;
    @NotEmpty(message = "facebook cannot be empty")
    private  String facebook;
    @NotEmpty(message = "github cannot be empty")
    private  String github;
    @NotEmpty(message = "linkedin cannot be empty")
    private  String linkedin;
    @NotEmpty(message = "role cannot be empty")
    private  String role;
    @NotEmpty(message = "department cannot be empty")
    private  String department;
    private MultipartFile image;

    private LocalDateTime date;

    public TeamsPojo(Teams teams) {
        this.id=teams.getId();
        this.email=teams.getEmail();
        this.contact=teams.getContact();
        this.fullname=teams.getFullname();
        this.address=teams.getAddress();
        this.facebook=teams.getFacebook();
        this.github=teams.getGithub();
        this.linkedin=teams.getLinkedin();
        this.department=teams.getDepartment();
        this.date=teams.getCreatedAt();

    }
}
