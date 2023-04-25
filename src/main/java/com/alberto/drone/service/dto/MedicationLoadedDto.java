package com.alberto.drone.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationLoadedDto {

    private String code;
    private String name;
    private int weight;
    private int quantity;

}
