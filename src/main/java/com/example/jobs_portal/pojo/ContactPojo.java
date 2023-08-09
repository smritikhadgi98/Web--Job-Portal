package com.example.jobs_portal.pojo;

import com.example.jobs_portal.entity.Contact;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactPojo {
    private Integer id;
    @NotEmpty(message = "firstname cannot be empty")
    private  String firstname;
    @NotEmpty(message = "lastname cannot be empty")
    private String lastname;
    @NotEmpty(message = "message cannot be empty")
    private  String message;
    @NotEmpty(message = "email cannot be empty")
    private  String email;
    @NotEmpty(message = "phone cannot be empty")
    private  String phone;

    private LocalDateTime date;

    public ContactPojo(Contact contact){
        this.id=contact.getId();
        this.email=contact.getEmail();
        this.message=contact.getMessage();
        this.phone=contact.getMobile_no();
        this.firstname=contact.getFirstname();
        this.lastname=contact.getLastname();
        this.date=contact.getCreatedAt();
    }
}
