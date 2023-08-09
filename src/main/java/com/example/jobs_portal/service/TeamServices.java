package com.example.jobs_portal.service;

import com.example.jobs_portal.entity.Teams;
import com.example.jobs_portal.entity.User;
import com.example.jobs_portal.pojo.TeamsPojo;
import com.example.jobs_portal.pojo.UserPojo;

import java.io.IOException;
import java.util.List;

public interface TeamServices {
    List<Teams> fetchAll();

    TeamsPojo save(TeamsPojo teamsPojo) throws IOException;

    Teams fetchById(Integer id);

    void deleteById(Integer id);

    Long countRows();
    long countRowsLastMonth();

}
