package com.alberto.drone.util.mapper;

import com.alberto.drone.repository.entity.Drone;
import com.alberto.drone.repository.entity.DroneMedication;
import com.alberto.drone.service.dto.DroneDto;
import com.alberto.drone.service.dto.MedicationLoadedDto;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DroneMapper {

    public DroneDto toDto(Drone drone) {

        if(Objects.nonNull(drone.getDroneMedicationLoads())){
            return DroneDto.builder()
                    .serialNumber(drone.getSerialNumber())
                    .model(drone.getModel())
                    .weightLimit(drone.getWeightLimit())
                    .batteryCapacity(drone.getBatteryCapacity())
                    .state(drone.getState())
                    .medicationList(drone.getDroneMedicationLoads().stream()
                            .filter(d -> d.getState().equals("A"))
                            .map(this::toLoadedDto)
                            .collect(Collectors.toList()))
                    .build();
        }else{
            return DroneDto.builder()
                    .serialNumber(drone.getSerialNumber())
                    .model(drone.getModel())
                    .weightLimit(drone.getWeightLimit())
                    .batteryCapacity(drone.getBatteryCapacity())
                    .state(drone.getState())
                    .build();
        }
    }

    public Drone toEntity(DroneDto droneDto) {
        return Drone.builder()
                .serialNumber(droneDto.getSerialNumber())
                .model(droneDto.getModel())
                .weightLimit(droneDto.getWeightLimit())
                .batteryCapacity(droneDto.getBatteryCapacity())
                .state(droneDto.getState()).build();
    }

    public MedicationLoadedDto toLoadedDto(DroneMedication droneMedication){
        return MedicationLoadedDto.builder()
                .name(droneMedication.getMedication().getName())
                .code(droneMedication.getMedication().getCode())
                .weight(droneMedication.getMedication().getWeight())
                .quantity(droneMedication.getQuantity())
                .build();

    }


}
