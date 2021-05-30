package com.hunza.services.catererservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "caterers")
public class Caterer implements Serializable {
    @Transient
    public static final String SEQUENCE_NAME = "db_sequence";
    @Id
    private Long id;
    @NotBlank(message = "Caterer Name cannot be Blank")
    private String name;
    @Valid
    private Location location;
    @Valid
    private Capacity capacity;
    @Valid
    private ContactInfo contactInfo;

}
