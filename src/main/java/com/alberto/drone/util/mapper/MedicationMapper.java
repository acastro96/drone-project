package com.alberto.drone.util.mapper;

import com.alberto.drone.repository.entity.Medication;
import com.alberto.drone.service.dto.MedicationDto;
import org.springframework.stereotype.Component;

@Component
public class MedicationMapper {

    public MedicationDto toDto(Medication medication){
        return MedicationDto.builder()
                .name(medication.getName())
                .weight(medication.getWeight())
                .code(medication.getCode())
                .image(medication.getImage()).build();
    }

    public Medication toEntity(MedicationDto medication){
        return Medication.builder()
                .name(medication.getName())
                .weight(medication.getWeight())
                .code(medication.getCode())
                .image(medication.getImage()).build();
    }

}
