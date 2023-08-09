package com.example.jobs_portal.service.impl;

import com.example.jobs_portal.entity.Teams;
import com.example.jobs_portal.pojo.TeamsPojo;
import com.example.jobs_portal.repo.TeamsRepo;
import com.example.jobs_portal.service.TeamServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamServices {
    public   final TeamsRepo teamsRepo;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/all_team";

    @Override
    public List<Teams> fetchAll() {
        return findAllInList(teamsRepo.findAll());
    }
    public List<Teams> findAllInList(List<Teams> list) {
        Stream<Teams> allJobsWithImage = list.stream().map(team ->
                        Teams.builder()
                                .id(team.getId())
                                .address(team.getAddress())
                                .contact(team.getAddress())
                                .email(team.getEmail())
                                .facebook(team.getFacebook())
                                .github(team.getGithub())
                                .linkedin(team.getLinkedin())
                                .department(team.getDepartment())
                                .fullname(team.getFullname())
                                .imageBase64(getImageBase64(team.getImage()))
                                .build()
        );
        list = allJobsWithImage.toList();
        return list;
    }


    @Override
    public TeamsPojo save(TeamsPojo teamsPojo) throws IOException {
        Teams teams;
        if (teamsPojo.getId() != null) {
            teams = teamsRepo.findById(teamsPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            teams = new Teams();
        }
        if(teamsPojo.getId()!=null){
            teams.setId(teamsPojo.getId());
        }
        teams.setCreatedAt(teams.getCreatedAt());

        teams.setEmail(teamsPojo.getEmail());
        teams.setFullname(teamsPojo.getFullname());
        teams.setAddress(teamsPojo.getAddress());
        teams.setContact(teamsPojo.getContact());
        teams.setFacebook(teamsPojo.getFacebook());
        teams.setGithub(teamsPojo.getGithub());
        teams.setLinkedin(teamsPojo.getLinkedin());
        teams.setDepartment(teamsPojo.getDepartment());
        if(teamsPojo.getImage()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, teamsPojo.getImage().getOriginalFilename());
            fileNames.append(teamsPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, teamsPojo.getImage().getBytes());
            teams.setImage(teamsPojo.getImage().getOriginalFilename());
        }
        teamsRepo.save(teams);
        return new TeamsPojo(teams);
    }


    @Override
    public Teams fetchById(Integer id) {
        return teamsRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public Long countRows() {
        return teamsRepo.countAllRows();
    }

    @Override
    public long countRowsLastMonth() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return teamsRepo.countByDateAfter(oneMonthAgo);
    }

    @Override
    public void deleteById(Integer id) {
        teamsRepo.deleteById(id);
    }
    public String getImageBase64(String fileName) {
        if (fileName!=null) {
            String filePath = System.getProperty("user.dir")+"/all_team/";
            File file = new File(filePath + fileName);
            byte[] bytes;
            try {
                bytes = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return Base64.getEncoder().encodeToString(bytes);
        }
        return null;
    }
}
