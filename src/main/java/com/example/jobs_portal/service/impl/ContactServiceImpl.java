package com.example.jobs_portal.service.impl;

import com.example.jobs_portal.entity.Contact;
import com.example.jobs_portal.entity.Jobs;
import com.example.jobs_portal.pojo.ContactPojo;
import com.example.jobs_portal.repo.ContactRepo;
import com.example.jobs_portal.service.ContactServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactServices {
    private  final ContactRepo contactRepo;

    @Override
    public ContactPojo save(ContactPojo contactPojo) {
        Contact contact = new Contact();
        if (contactPojo.getId() != null) {
            contact.setId(contactPojo.getId());
        }
        contact.setCreatedAt(contact.getCreatedAt());

        contact.setEmail(contactPojo.getEmail());
        contact.setFirstname(contactPojo.getFirstname());
        contact.setLastname(contactPojo.getLastname());
        contact.setMobile_no(contactPojo.getPhone());
        contact.setMessage(contactPojo.getMessage());
        contactRepo.save(contact);
        return new ContactPojo(contact);
    }

    @Override
    public List<Contact> fetchAll() {
        return contactRepo.findAll();
    }

    @Override
    public long countRowsLastMonth() {
        LocalDate oneMonthAgo = LocalDate.now();
        System.out.println(oneMonthAgo);
        return contactRepo.countByDateAfter(oneMonthAgo);
    }

    @Override
    public void deleteById(Integer id) {
        contactRepo.deleteById(id);
    }
}
