package com.example.jobs_portal.service.impl;

import com.example.jobs_portal.entity.Jobs;
import com.example.jobs_portal.entity.User;
import com.example.jobs_portal.pojo.JobsPojo;
import com.example.jobs_portal.repo.JobsRepo;
import com.example.jobs_portal.service.JobsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JobsServiceImpl implements JobsService {
    @Autowired
    public final JobsRepo jobsRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/jobs_images";


    @Override
    public List<Jobs> fetchAll() {
        return findAllInList(jobsRepo.findAll());
    }

    public List<Jobs> findAllInList(List<Jobs> list){
        Stream<Jobs> allJobsWithImage=list.stream().map(jobs ->
                Jobs.builder()
                        .id(jobs.getId())
                        .jobTitle(jobs.getJobTitle())
                        .address(jobs.getAddress())
                        .time_description(jobs.getTime_description())
                        .type(jobs.getType())
                        .imageBase64(getImageBase64(jobs.getImage()))
                        .category(jobs.getCategory())
                        .full_description(jobs.getFull_description())
                        .salary(jobs.getSalary())
                        .contact(jobs.getContact())
                        .skill1(jobs.getSkill1())
                        .skill2(jobs.getSkill2())
                        .skill3(jobs.getSkill3())
                        .skill4(jobs.getSkill4())
                        .qualification1(jobs.getQualification1())
                        .qualification2(jobs.getQualification2())
                        .qualification3(jobs.getQualification3())
                        .education(jobs.getEducation())
                        .Experience1(jobs.getExperience1())
                        .Experience2(jobs.getExperience2())
                        .contact(jobs.getContact())
//                        .imageBase64(jobs.getImage())
                        .build()
        );

        list = allJobsWithImage.toList();
        return list;
    }
    @Override
    public JobsPojo save(JobsPojo jobsPojo) throws IOException {
        Jobs jobs;
        if (jobsPojo.getId() != null) {
            jobs = jobsRepo.findById(jobsPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            jobs = new Jobs();
        }
        if(jobsPojo.getId()!=null){
            jobs.setId(jobsPojo.getId());
        }
        jobs.setCreatedAt(jobs.getCreatedAt());


        jobs.setTime_description(jobsPojo.getTime_description());
        jobs.setType(jobsPojo.getType());
        jobs.setCategory(jobsPojo.getCategory());
        jobs.setJobTitle(jobsPojo.getJob_title());
        jobs.setAddress(jobsPojo.getAddress());
        jobs.setContact(jobsPojo.getContact());
        jobs.setSalary(jobsPojo.getSalary());
        jobs.setFull_description(jobsPojo.getFull_description());
        jobs.setEducation(jobsPojo.getEducation());
        jobs.setQualification1(jobsPojo.getQualification1());
        jobs.setQualification2(jobsPojo.getQualification2());
        jobs.setQualification3(jobsPojo.getQualification3());
        jobs.setSkill1(jobsPojo.getSkill1());
        jobs.setSkill2(jobsPojo.getSkill2());
        jobs.setSkill3(jobsPojo.getSkill3());
        jobs.setSkill4(jobsPojo.getSkill4());
        jobs.setExperience1(jobsPojo.getExperience1());
        jobs.setExperience2(jobsPojo.getExperience2());
        if(jobsPojo.getImage()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, jobsPojo.getImage().getOriginalFilename());
            fileNames.append(jobsPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, jobsPojo.getImage().getBytes());

            jobs.setImage(jobsPojo.getImage().getOriginalFilename());
        }
        jobsRepo.save(jobs);
        return new JobsPojo(jobs);
    }

    @Override
    public void deleteById(Integer id) {
        jobsRepo.deleteById(id);
    }

    public List<Jobs> getFourRandomData() {
        return findAllInList(jobsRepo.findFourRandomData());
    }

    public List<Jobs> findJobsByFirstLetter(String letter) {
        return jobsRepo.findByNameStartingWith(letter);
    }

    @Override
    public Jobs fetchById(Integer id) {
        return jobsRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
    }


    @Override
    public Long countRows() {
        return jobsRepo.countAllRows();
    }


    @Override
    public long countRowsLastMonth() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return jobsRepo.countByDateAfter(oneMonthAgo);
    }

    public String getImageBase64(String fileName) {
        if (fileName!=null) {
            String filePath = System.getProperty("user.dir")+"\\jobs_images\\";
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
