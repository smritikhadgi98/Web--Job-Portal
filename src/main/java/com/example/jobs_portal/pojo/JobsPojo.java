package com.example.jobs_portal.pojo;

import com.example.jobs_portal.entity.Jobs;
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
public class JobsPojo {
    private Integer id;
    @NotEmpty(message = "title cannot be empty")
    private String job_title;
    @NotEmpty(message = "time cannot be empty")
    private  String time_description;

    @NotEmpty(message = "type cannot be empty")
    private  String type;
    @NotEmpty(message = "address cannot be empty")
    private  String address;
    @NotEmpty(message = "catrgory cannot be empty")
    private  String category;
    @NotEmpty(message = "contact cannot be empty")
    private  String contact;
    @NotEmpty(message = "full_description cannot be empty")
    private  String full_description;
    @NotEmpty(message = "salary cannot be empty")
    private  String salary;
    @NotEmpty(message = "skill1 cannot be empty")
    private String skill1;
    @NotEmpty(message = "skill2 cannot be empty")
    private String  skill2;
    @NotEmpty(message = "skill3 cannot be empty")
    private String skill3;
    @NotEmpty(message = "skill4 cannot be empty")
    private String skill4;
    @NotEmpty(message = "education cannot be empty")
    private String education;
    @NotEmpty(message = "qualification cannot be empty")
    private String qualification1;
    @NotEmpty(message = "qualification2 cannot be empty")
    private String qualification2;
    @NotEmpty(message = "qualification3 cannot be empty")
    private String qualification3;
    @NotEmpty(message = "experience cannot be empty")
    private String Experience1;

    @NotEmpty(message = "Experience cannot be empty")
    private String Experience2;

    private MultipartFile image;

    private LocalDateTime date;


    public JobsPojo(Jobs jobs) {
        this.id=jobs.getId();
        this.job_title=jobs.getJobTitle();
        this.time_description=jobs.getTime_description();
        this.type=jobs.getType();
        this.address=jobs.getAddress();
        this.category=jobs.getCategory();
        this.contact=jobs.getContact();
        this.full_description=jobs.getFull_description();
        this.skill1=jobs.getSkill1();
        this.skill2=jobs.getSkill2();
        this.skill3=jobs.getSkill3();
        this.skill4=jobs.getSkill4();
        this.education=jobs.getEducation();
        this.qualification1=jobs.getQualification1();
        this.qualification2=jobs.getQualification2();
        this.qualification3=jobs.getQualification3();
        this.salary=jobs.getSalary();
        this.Experience1=jobs.getExperience1();
        this.Experience2=jobs.getExperience2();
        this.date=jobs.getCreatedAt();

    }
}
