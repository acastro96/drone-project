package com.alberto.drone.util.mapper;

import com.alberto.drone.repository.entity.Drone;
import com.alberto.drone.repository.entity.Medication;
import com.alberto.drone.repository.entity.DroneMedication;
import com.alberto.drone.service.dto.DroneMedicationDto;
import org.springframework.stereotype.Component;

@Component
public class DroneMedicationMapper {

    public DroneMedicationDto toDto(DroneMedication droneMedication) {
        return DroneMedicationDto.builder()
                .droneId(droneMedication.getDrone().getId())
                .medId(droneMedication.getMedication().getId())
                .medicationWeight(droneMedication.getMedication().getWeight())
                .quantity(droneMedication.getQuantity())
                .state(droneMedication.getState()).build();
    }

    public DroneMedication toEntity(DroneMedicationDto droneMedicationDto){
        return DroneMedication.builder()
                .drone(Drone.builder().id(droneMedicationDto.getDroneId()).build())
                .medication(Medication.builder().id(droneMedicationDto.getMedId()).build())
                .state(droneMedicationDto.getState())
                .quantity(droneMedicationDto.getQuantity()).build();
    }

}
