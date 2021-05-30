package com.hunza.services.catererservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {

    @NotBlank(message = "City Name cannot be Blank")
    @Pattern(regexp = "[a-zA-Z]+", message = "must not contain digits or special characters")
    private String cityName;
    @NotBlank(message = "Street Name and Number cannot be Blank")
    private String streetNameAndNumber;
    private String postalCode;

}
