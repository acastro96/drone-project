package com.alberto.drone.util.mapper;

import com.alberto.drone.repository.entity.DroneMedication;
import com.alberto.drone.service.dto.DroneMedicationDto;
import org.springframework.stereotype.Component;

@Component
public class DroneMedicationMapper {

    public DroneMedicationDto toDto(DroneMedication droneMedication) {
        return DroneMedicationDto.builder()
                .droneSerialNumber(droneMedication.getDrone().getSerialNumber())
                .medicationCode(droneMedication.getMedication().getCode())
                .medicationWeight(droneMedication.getMedication().getWeight())
                .quantity(droneMedication.getQuantity())
                .state(droneMedication.getState()).build();
    }

}
