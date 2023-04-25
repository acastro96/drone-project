package com.alberto.drone.util.mapper;

import com.alberto.drone.repository.entity.Medication;
import com.alberto.drone.service.dto.MedicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MedicationMapper {

    @Autowired
    private DroneMedicationMapper droneMedicationMapper;

    public MedicationDto toDto(Medication medication){

        if(Objects.nonNull(medication.getDroneMedicationLoads())){
            return MedicationDto.builder()
                    .name(medication.getName())
                    .weight(medication.getWeight())
                    .code(medication.getCode())
                    .image(medication.getImage())
                    .droneMedicationList(medication.getDroneMedicationLoads().stream()
                            .map(droneMedicationMapper::toDto)
                            .collect(Collectors.toList())).build();
        }else{
            return MedicationDto.builder()
                    .name(medication.getName())
                    .weight(medication.getWeight())
                    .code(medication.getCode())
                    .image(medication.getImage()).build();
        }


    }

    public Medication toEntity(MedicationDto medication){
        return Medication.builder()
                .name(medication.getName())
                .weight(medication.getWeight())
                .code(medication.getCode())
                .image(medication.getImage())
                .build();
    }

}
