package com.example.jobs_portal;

import com.example.jobs_portal.entity.Application;
import com.example.jobs_portal.entity.Jobs;
import com.example.jobs_portal.entity.Teams;
import com.example.jobs_portal.repo.ApplicationRepo;
import com.example.jobs_portal.repo.JobsRepo;
import com.example.jobs_portal.repo.TeamsRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationRepoTest {

    @Autowired
    private ApplicationRepo applicationRepo;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveApplication() {

        Application application = Application.builder()
                .address("ktm")
                .file("resume.pdf")
                .gender("male")
                .id_num("1234")
                .id_type("citized")
                .build();
        applicationRepo.save(application);
        Assertions.assertThat(application.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void getJobsTest() {
        Application application = applicationRepo.findById(1).get();
        Assertions.assertThat(application.getId()).isEqualTo(1);
    }

    //
    @Test
    @Order(3)
    public void fetchAll() {
        List<Application> application = applicationRepo.findAll();
        Assertions.assertThat(application.size()).isGreaterThan(0);
    }
    //
    @Test
    @Order(4)
    @Rollback(value = false)
    public void Update() {
        Application application = applicationRepo.findById(1).get();
        application.setGender("Male");
        Application application1 = applicationRepo.save(application);
        Assertions.assertThat(application1.getGender()).isEqualTo("Male");
    }


    @Test
    @Order(5)
    @Rollback(value = false)
    public void Delete(){
        Application application=applicationRepo.findById(1).get();
        applicationRepo.delete(application);
        Application application1=null;
        Optional<Application> teamsOptional=applicationRepo.findByAddress("ktm");
        if(teamsOptional.isPresent()){
            application1 = teamsOptional.get();
        }
        Application application2=applicationRepo.save(application);
        Assertions.assertThat(application1).isNull();
    }


}