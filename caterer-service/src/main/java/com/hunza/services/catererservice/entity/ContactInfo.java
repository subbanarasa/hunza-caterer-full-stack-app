package com.hunza.services.catererservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo implements Serializable {

    private String phoneNumber;
    @NotBlank(message = "Mobile cannot be Blank")
    private String mobileNumber;
    @Email(message = "Email should be valid")
    private String emailAddress;


}
