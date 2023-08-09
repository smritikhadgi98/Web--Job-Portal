package com.example.jobs_portal.repo;

import com.example.jobs_portal.entity.Application;
import com.example.jobs_portal.entity.Jobs;
import com.example.jobs_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Integer> {
    @Query(value = "SELECT * FROM application where Applied_User=?1", nativeQuery = true)
    List<Application> findApplicationById(Integer id);



    @Query(value = "SELECT * FROM application where address=?1", nativeQuery = true)
    Optional<Application> findByAddress(String address);


    @Query(value = "select COUNT(*) from application", nativeQuery = true)
    Long countAllRows();

    @Query(value = "SELECT COUNT(*) FROM application WHERE created_at >= ?1", nativeQuery = true)
    Long countByDateAfter(LocalDate date);

}
