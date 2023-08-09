package com.example.jobs_portal.pojo;

import com.example.jobs_portal.entity.User;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {
    private Integer id;

    @NotEmpty(message = "email cannot be empty")
    private String email;
    @NotEmpty(message = "mobile cannot be empty")
    private  String mobile_no;
    @NotEmpty(message = "fullname cannot be empty")
    private  String fullname;
    @NotEmpty(message = "password cannot be empty")
    private  String password;
    private MultipartFile image;

    private LocalDateTime date;

    public UserPojo(User user) {
      this.id=user.getId();
      this.email=user.getEmail();
      this.mobile_no=user.getMobileNo();
      this.fullname=user.getFullname();
      this.date=user.getCreatedAt();
      this.password=user.getPassword();
    }
}
