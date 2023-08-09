package com.example.jobs_portal;

import com.example.jobs_portal.entity.Jobs;
import com.example.jobs_portal.entity.Teams;
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
public class TeamsRepoTest {

    @Autowired
    private TeamsRepo teamsRepo;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveTeams() {

        Teams teams = Teams.builder()
                .address("ktm")
                .contact("1231231234")
                .email("abi@gmail.com")
                .department("Programming")
                .facebook("www.facebook.com")
                .github("www.github.com")
                .linkedin("www.linked.com")
                .fullname("Abishek")
                .image("abi.jgp")

                .build();
//
        teamsRepo.save(teams);
        Assertions.assertThat(teams.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void getTeamsTest() {
        Teams teams = teamsRepo.findById(1).get();
        Assertions.assertThat(teams.getId()).isEqualTo(1);
    }

//
    @Test
    @Order(3)
    public void fetchAll() {
        List<Teams> teams = teamsRepo.findAll();
        Assertions.assertThat(teams.size()).isGreaterThan(0);
    }
//
    @Test
    @Order(4)
    @Rollback(value = false)
    public void Update() {
        Teams teams = teamsRepo.findById(1).get();
        teams.setDepartment("Programming");
        Teams teams1 = teamsRepo.save(teams);
        Assertions.assertThat(teams1.getDepartment()).isEqualTo("Programming");
    }
//
    @Test
    @Order(5)
    @Rollback(value = false)
    public void Delete(){
        Teams teams=teamsRepo.findById(1).get();
        teamsRepo.delete(teams);
        Teams teams1=null;
        Optional<Teams> teamsOptional=teamsRepo.findBydepartment("Programming");
        if(teamsOptional.isPresent()){
            teams1 = teamsOptional.get();
        }
        Teams teams2=teamsRepo.save(teams);
//        Assertions.assertThat(jobsu.getJobTitle()).isEqualTo("Part time");
        Assertions.assertThat(teams1).isNull();
    }


}