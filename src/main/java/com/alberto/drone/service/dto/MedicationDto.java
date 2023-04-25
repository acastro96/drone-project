package com.alberto.drone.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDto {

    @NotBlank(message = "The name is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9_\\-]*$")
    private String name;

    @NotNull(message = "The weight is mandatory")
    private int weight;

    @NotBlank(message = "The code is mandatory")
    @Pattern(regexp = "^[A-Z0-9_\\-]*$")
    private String code;

    private String image;

    private List<DroneMedicationDto> droneMedicationList;

}
