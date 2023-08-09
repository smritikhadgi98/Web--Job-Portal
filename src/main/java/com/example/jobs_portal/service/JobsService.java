package com.example.jobs_portal.service;

import com.example.jobs_portal.entity.Jobs;
import com.example.jobs_portal.entity.User;
import com.example.jobs_portal.pojo.JobsPojo;

import java.io.IOException;
import java.util.List;

public interface JobsService {
    List<Jobs> fetchAll();
    JobsPojo save(JobsPojo jobsPojo) throws IOException;
    void deleteById(Integer id);
    Jobs fetchById(Integer id);

//    List<Jobs> fetchAllById(Integer id);


    List<Jobs> getFourRandomData();

    List<Jobs> findJobsByFirstLetter(String letter);

    Long countRows();

    long countRowsLastMonth();


}
