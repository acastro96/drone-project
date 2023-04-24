package com.alberto.drone.service;

import com.alberto.drone.service.contract.IDroneService;
import com.alberto.drone.service.dto.AvailableDronesDto;
import com.alberto.drone.service.dto.DroneDto;
import com.alberto.drone.repository.contract.IDroneRepository;
import com.alberto.drone.util.enums.EnumStates;
import com.alberto.drone.util.mapper.DroneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DroneService implements IDroneService {

    @Autowired
    private IDroneRepository droneRepository;

    @Autowired
    private DroneMapper droneMapper;


    @Override
    public DroneDto saveDrone(DroneDto droneDto) {
        return droneMapper.toDto(droneRepository.save(droneMapper.toEntity(droneDto)));
    }

    @Override
    public DroneDto getDrone(String serialNumber) {
        return droneMapper.toDto(droneRepository.findBySerialNumber(serialNumber));
    }

    @Override
    public List<AvailableDronesDto> getAvailableDronesForLoading() {
        int currentGr;
        List<AvailableDronesDto> availableDronesList = new ArrayList<>();

        List<DroneDto> droneList =
                droneRepository.findByState(EnumStates.LOADING.value).stream()
                .map(droneMapper::toDto)
                .collect(Collectors.toList());

        for (DroneDto drone : droneList) {
            currentGr = drone.getDroneMedicationList().stream()
                    .filter(d -> d.getState().equals("A"))
                    .mapToInt(d -> d.getQuantity() * d.getMedicationWeight())
                    .sum();

            if (currentGr < drone.getWeightLimit()) {
                availableDronesList.add(AvailableDronesDto.builder()
                        .drone(drone)
                        .freeWeight(drone.getWeightLimit() - currentGr)
                        .build());
            }
        }

        return availableDronesList;
    }

    @Override
    public Integer getDroneBatteryLevel(String serialNumber) {
        return droneRepository.findBySerialNumber(serialNumber).getBatteryCapacity();
    }


}
