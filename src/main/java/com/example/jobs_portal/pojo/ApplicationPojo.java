package com.example.jobs_portal.pojo;

import com.example.jobs_portal.entity.Application;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationPojo {
    private Integer id;
    @NotEmpty(message = "gender cannot be empty")
    private  String gender;
    @NotEmpty(message = "address cannot be empty")
    private String address;
    @NotEmpty(message = "id-type cannot be empty")
    private  String id_type;
    @NotEmpty(message = "id_num cannot be empty")
    private  String id_num;
//    private  String status;
    private int  Applied_jobs_FK;
//    @NotEmpty(message = "Applied_user cannot be empty")
    private int  Applied_user;
    private MultipartFile file;
    private LocalDateTime date;



    public ApplicationPojo(Application application) {
        this.id=application.getId();
        this.address=application.getAddress();
        this.gender=application.getGender();
        this.id_type=application.getId_type();
        this.id_num=application.getId_num();
        this.Applied_jobs_FK=application.getJobs_id().getId();
        this.Applied_user=application.getUser_id().getId();
        this.date=application.getCreatedAt();
    }
}
