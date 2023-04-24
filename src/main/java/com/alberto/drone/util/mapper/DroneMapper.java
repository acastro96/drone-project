package com.alberto.drone.util.mapper;

import com.alberto.drone.repository.entity.Drone;
import com.alberto.drone.service.dto.DroneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DroneMapper {

    @Autowired
    private DroneMedicationMapper droneMedicationMapper;

    public Drone toEntity(DroneDto droneDto){
        return Drone.builder()
                .serialNumber(droneDto.getSerialNumber())
                .model(droneDto.getModel())
                .weightLimit(droneDto.getWeightLimit())
                .batteryCapacity(droneDto.getBatteryCapacity())
                .state(droneDto.getState()).build();
    }

    public DroneDto toDto(Drone drone){
        return DroneDto.builder()
                .serialNumber(drone.getSerialNumber())
                .model(drone.getModel())
                .weightLimit(drone.getWeightLimit())
                .batteryCapacity(drone.getBatteryCapacity())
                .state(drone.getState())
                .droneMedicationList(drone.getDroneMedicationLoads().stream()
                        .map(droneMedicationMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }



}
