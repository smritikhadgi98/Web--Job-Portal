package com.example.jobs_portal.service;

import com.example.jobs_portal.entity.Application;
import com.example.jobs_portal.entity.Jobs;
import com.example.jobs_portal.entity.User;
import com.example.jobs_portal.pojo.ApplicationPojo;
import com.example.jobs_portal.pojo.UserPojo;

import java.io.IOException;
import java.util.List;

public interface ApplicationService {

    List<Application> fetchAll();

    ApplicationPojo save(ApplicationPojo applicationPojo) throws IOException;
    void deleteById(Integer id);
    List<Application> findApplicationById(Integer id);
    Application fetchById(Integer id);

    Long countRows();

    long countRowsLastMonth();




}
