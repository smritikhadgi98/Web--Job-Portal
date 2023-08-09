package com.example.jobs_portal.repo;

import com.example.jobs_portal.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Integer> {
    @Query(value = "SELECT COUNT(*) FROM contact WHERE created_at >= ?1", nativeQuery = true)
    Long countByDateAfter(LocalDate date);
}
