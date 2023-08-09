package com.example.jobs_portal;

import com.example.jobs_portal.entity.Jobs;
import com.example.jobs_portal.entity.Teams;
import com.example.jobs_portal.entity.User;
import com.example.jobs_portal.repo.JobsRepo;
import com.example.jobs_portal.repo.TeamsRepo;
import com.example.jobs_portal.repo.UserRepo;
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
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUser() {

        User user = User.builder()
                .email("abi@gmail.com")
                .password("1234")
                .fullname("code")
                .image("abi.jgp")
                .mobileNo("123123")

                .build();
//
        userRepo.save(user);
        Assertions.assertThat(user.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void getUserTest() {
        User user = userRepo.findById(1).get();
        Assertions.assertThat(user.getId()).isEqualTo(1);
    }

    //
    @Test
    @Order(3)
    public void fetchAll() {
        List<User> user = userRepo.findAll();
        Assertions.assertThat(user.size()).isGreaterThan(0);
    }
    //
    @Test
    @Order(4)
    @Rollback(value = false)
    public void Update() {
        User user = userRepo.findById(1).get();
        user.setFullname("Abishek");
        User user1 = userRepo.save(user);
        Assertions.assertThat(user1.getFullname()).isEqualTo("Abishek");
    }
    //
    @Test
    @Order(5)
    @Rollback(value = false)
    public void Delete(){
        User user=userRepo.findById(1).get();
        userRepo.delete(user);
        User user1=null;
        Optional<User> userOptional=userRepo.findByEmail("abi@gamil.com");
        if(userOptional.isPresent()){
            user1 = userOptional.get();
        }
        User user2=userRepo.save(user);
        Assertions.assertThat(user1).isNull();
    }


}