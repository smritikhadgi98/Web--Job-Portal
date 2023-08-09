package com.example.jobs_portal.service.impl;

import com.example.jobs_portal.config.PasswordEncoderUtil;
import com.example.jobs_portal.entity.Application;
import com.example.jobs_portal.entity.Jobs;
import com.example.jobs_portal.entity.User;
import com.example.jobs_portal.exception.AppException;
import com.example.jobs_portal.pojo.ApplicationPojo;
import com.example.jobs_portal.pojo.UserPojo;
import com.example.jobs_portal.repo.ApplicationRepo;
import com.example.jobs_portal.repo.JobsRepo;
import com.example.jobs_portal.repo.UserRepo;
import com.example.jobs_portal.service.ApplicationService;
import com.example.jobs_portal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
public class ApplicationServiceImp implements ApplicationService {
    public final ApplicationRepo applicationRepo;
    public  final JobsRepo jobsRepo;
    public  final UserRepo userRepo;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/jobs_application_files";


    public List<Application> fetchAll(){
        return findAllInList(applicationRepo.findAll());
    }

    public List<Application> findAllInList(List<Application> list){
        Stream<Application> allApplication=list.stream().map(application ->
                        Application.builder()
                                .id(application.getId())
                                .user_id(application.getUser_id())
                                .jobs_id(application.getJobs_id())
                                .address(application.getAddress())
                                .id_num(application.getId_num())
                                .id_type(application.getId_type())
                                .resume(getFileName(application.getFile()))
                                .gender(application.getGender())
//                                .status(application.getStatus())
                                .build()
        );

        list = allApplication.toList();
        return list;
    }

    @Override
    public ApplicationPojo save(ApplicationPojo applicationPojo) throws IOException {
        Application application;
        if (applicationPojo.getId() != null) {
            application = applicationRepo.findById(applicationPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            application = new Application();
        }
        if(applicationPojo.getId()!=null){
            application.setId(application.getId());
        }
//        Application application=new Application();
        application.setCreatedAt(application.getCreatedAt());

        application.setId_type(applicationPojo.getId_type());
        application.setId_num(applicationPojo.getId_num());
        application.setGender(applicationPojo.getGender());
        application.setAddress(applicationPojo.getAddress());
        application.setJobs_id(jobsRepo.findById(applicationPojo.getApplied_jobs_FK()).orElseThrow());
        application.setUser_id(userRepo.findById(applicationPojo.getApplied_user()).orElseThrow());
//        application.setStatus(applicationPojo.getStatus());

        if(applicationPojo.getFile()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, applicationPojo.getFile().getOriginalFilename());
            fileNames.append(applicationPojo.getFile().getOriginalFilename());
            Files.write(fileNameAndPath, applicationPojo.getFile().getBytes());

            application.setFile(applicationPojo.getFile().getOriginalFilename());
        }

        applicationRepo.save(application);
        return new ApplicationPojo(application);
    }

    @Override
    public void deleteById(Integer id) {
        applicationRepo.deleteById(id);
    }

    @Override
    public List<Application> findApplicationById(Integer id) {
        return findAllInList(applicationRepo.findApplicationById(id));
    }

    @Override
    public Application fetchById(Integer id) {
        Application application= applicationRepo.findById(id).orElseThrow(()->new RuntimeException("not found application"));
        application=Application.builder()
                .id(application.getId())
                .user_id(application.getUser_id())
                .jobs_id(application.getJobs_id())
                .address(application.getAddress())
                .gender(application.getGender())
                .id_type(application.getId_type())
                .id_num(application.getId_num())
//                .status(application.getStatus())
                .file(application.getFile())
                .build();
        return application;
    }

    @Override
    public Long countRows() {
        return applicationRepo.countAllRows();
    }

    @Override
    public long countRowsLastMonth() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return applicationRepo.countByDateAfter(oneMonthAgo);
    }

    public String getFileName(String fileName) {
        String filePath = System.getProperty("user.dir") + "/jobs_application_files/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }



}

