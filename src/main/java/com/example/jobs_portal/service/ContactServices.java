package com.example.jobs_portal.service;

import com.example.jobs_portal.entity.Contact;
import com.example.jobs_portal.pojo.ContactPojo;

import java.util.*;

public interface ContactServices {
    ContactPojo save(ContactPojo contactPojo);
    List<Contact> fetchAll();
    long countRowsLastMonth();

    void deleteById(Integer id);

}
