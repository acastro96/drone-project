package com.alberto.drone.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneDto {

    @NotBlank(message = "The serial number is mandatory")
    @Max(value = 100, message = "The serial number must have 100 characters or less")
    private String serialNumber;

    @NotBlank(message = "The model is mandatory")
    @Pattern(regexp = "Lightweight|Middleweight|Cruiserweight|Heavyweight")
    private String model;

    @NotBlank(message = "The weight is mandatory")
    @Max(value = 500, message = "The weight must have 500 gr or less")
    private int weightLimit;

    @NotBlank(message = "The battery capacity is mandatory")
    private int batteryCapacity;

    @NotBlank(message = "The state is mandatory")
    @Pattern(regexp = "IDLE|LOADING|LOADED|DELIVERING|DELIVERED|RETURNING")
    private String state;

    private List<DroneMedicationDto> droneMedicationList;

}
