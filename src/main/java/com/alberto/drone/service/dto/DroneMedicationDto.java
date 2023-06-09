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

    private String droneSerialNumber;
    private String medicationCode;
    private int medicationWeight;
    private int quantity;
    private String state;

}
