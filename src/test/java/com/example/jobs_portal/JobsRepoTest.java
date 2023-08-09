package com.example.jobs_portal;

import com.example.jobs_portal.entity.Jobs;
import com.example.jobs_portal.repo.JobsRepo;
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
public class JobsRepoTest {

    @Autowired
    private JobsRepo jobsRepo;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveJobs() {

        Jobs jobs = Jobs.builder()
                .jobTitle("computer")
                .time_description("10-5")
                .type("fulltime")
                .address("btm")
                .category("it")
                .full_description("full_desc")
                .salary("20000")
                .contact("0980980998")
                .skill1("code")
                .skill2("code")
                .skill3("code")
                .skill4("code")
                .education("full_desc")
                .qualification1("full_desc")
                .qualification2("full_desc")
                .qualification3("full_desc")
                .Experience1("full_desc")
                .Experience2("full_desc")
                .build();
//
        jobsRepo.save(jobs);
        Assertions.assertThat(jobs.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void getJobsTest() {
        Jobs jobs = jobsRepo.findById(1).get();
        Assertions.assertThat(jobs.getId()).isEqualTo(1);
    }


    @Test
    @Order(3)
    public void fetchAll() {
        List<Jobs> jobsList = jobsRepo.findAll();
        Assertions.assertThat(jobsList.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void Update() {
        Jobs jobs = jobsRepo.findById(1).get();
        jobs.setJobTitle("Part time");
        Jobs jobsu = jobsRepo.save(jobs);
        Assertions.assertThat(jobsu.getJobTitle()).isEqualTo("Part time");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void Delete(){
        Jobs jobs=jobsRepo.findById(1).get();
        jobsRepo.delete(jobs);
        Jobs jobss=null;
        Optional<Jobs> jobsOptional=jobsRepo.findByjob_title("computer");
            if(jobsOptional.isPresent()){
                jobss = jobsOptional.get();
            }
        Jobs jobsu=jobsRepo.save(jobs);
//        Assertions.assertThat(jobsu.getJobTitle()).isEqualTo("Part time");
            Assertions.assertThat(jobss).isNull();
    }


}