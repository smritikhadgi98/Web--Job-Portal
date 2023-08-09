package com.example.jobs_portal.service;


import com.example.jobs_portal.entity.User;
import com.example.jobs_portal.pojo.UserPojo;

import java.io.IOException;
import java.util.List;

public interface UserService {
//    UserPojo save(UserPojo userPojo);
    List<User> fetchAll();

    UserPojo save(UserPojo userPojo) throws IOException;

     User fetchById(Integer id);

     void deleteById(Integer id);

    User findByEmail(String email);
//    public boolean checkEmail(String email);

    Long countRows();
    long countRowsLastMonth();
}
