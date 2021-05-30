package com.hunza.services.catererservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Capacity implements Serializable {

    @Positive(message = "Minimum Guests should be positive number")
    private Integer minNumberOfGuests;

    @Positive(message = "Maximum Guests should be positive number")
    private Integer maxNumberOfGuests;

}
