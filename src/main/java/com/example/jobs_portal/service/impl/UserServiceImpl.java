package com.example.jobs_portal.service.impl;

import com.example.jobs_portal.config.PasswordEncoderUtil;
import com.example.jobs_portal.entity.User;
import com.example.jobs_portal.exception.AppException;
import com.example.jobs_portal.pojo.UserPojo;
import com.example.jobs_portal.repo.UserRepo;
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
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public final UserRepo userRepo;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/Jobs_Image_Server";

    @Override
    public UserPojo save(UserPojo userPojo) throws IOException {
        User user;
        if (userPojo.getId() != null) {
            user = userRepo.findById(userPojo.getId()).orElseThrow(() -> new RuntimeException("Not Found"));
        } else {
            user = new User();
        }
//        if(userPojo.getId()!=null){
//            user.setId(user.getId());
//        }
        user.setCreatedAt(user.getCreatedAt());
        user.setEmail(userPojo.getEmail());
        user.setFullname(userPojo.getFullname());
        user.setMobileNo(userPojo.getMobile_no());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userPojo.getPassword()));

        if(userPojo.getImage()!=null){
            StringBuilder fileNames = new StringBuilder();
            System.out.println(UPLOAD_DIRECTORY);
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, userPojo.getImage().getOriginalFilename());
            fileNames.append(userPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, userPojo.getImage().getBytes());
            user.setImage(userPojo.getImage().getOriginalFilename());
        }

        userRepo.save(user);
        return new UserPojo(user);
    }

    public List<User> fetchAll(){
        return this.userRepo.findAll();
    }

    @Override
    public User fetchById(Integer id) {
        User user= userRepo.findById(id).orElseThrow(()->new RuntimeException("not found"));
        user=User.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .mobileNo(user.getMobileNo())
                .imageBase64(getImageBase64(user.getImage()))
                .build();
        return user;
    }


    @Override
    public void deleteById(Integer id) {
        userRepo.deleteById(id);

    }

    @Override
    public User findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));
        user=User.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .email(user.getEmail())
                .mobileNo(user.getMobileNo())
                .imageBase64(getImageBase64(user.getImage()))
                .build();
//        return user;
        return user;
    }

    @Override
    public Long countRows() {
        return userRepo.countAllRows();
    }

    @Override
    public long countRowsLastMonth() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return userRepo.countByDateAfter(oneMonthAgo);
    }


    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/Jobs_Image_Server/";
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

