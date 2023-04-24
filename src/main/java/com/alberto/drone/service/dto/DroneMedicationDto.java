package com.alberto.drone.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneMedicationDto {

    private Long droneId;
    private Long medId;
    private int medicationWeight;
    private int quantity;
    private String state;

}
