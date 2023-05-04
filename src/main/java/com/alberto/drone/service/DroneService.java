package com.alberto.drone.service;

import com.alberto.drone.exception.BusinessException;
import com.alberto.drone.repository.contract.IDroneRepository;
import com.alberto.drone.repository.entity.Drone;
import com.alberto.drone.service.contract.IDroneService;
import com.alberto.drone.service.dto.AvailableDronesDto;
import com.alberto.drone.service.dto.DroneDto;
import com.alberto.drone.util.enums.EnumStates;
import com.alberto.drone.util.mapper.DroneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DroneService implements IDroneService {

    @Autowired
    private IDroneRepository droneRepository;

    @Autowired
    private DroneMapper droneMapper;


    @Override
    public DroneDto saveDrone(DroneDto droneDto) {
        Optional<Drone> oDrone = droneRepository.findBySerialNumber(droneDto.getSerialNumber());
        if (oDrone.isPresent()) {
            throw new BusinessException("This serial number is already use by a registered drone");
        }
        return droneMapper.toDto(droneRepository.save(droneMapper.toEntity(droneDto)));
    }

    @Override
    public DroneDto findDroneBySerial(String serialNumber) {
        if (serialNumber.isEmpty()) {
            throw new BusinessException("The serial number is empty, please send it.");
        }
        Optional<Drone> oDrone = droneRepository.findBySerialNumber(serialNumber);

        return droneMapper.toDto(
                oDrone.orElseThrow(
                        () -> new BusinessException("There is no drone with this serial number, please check.")));
    }

    @Override
    public List<AvailableDronesDto> findAvailableDronesForLoading() {
        int currentGr;
        List<AvailableDronesDto> availableDronesList = new ArrayList<>();

        List<Drone> droneList =
                droneRepository.findByStates(Arrays.asList(EnumStates.LOADING.value, EnumStates.IDLE.value));

        for (Drone dr : droneList) {
            currentGr = dr.getDroneMedicationLoads().stream()
                    .filter(d -> d.getState().equals("A"))
                    .mapToInt(d -> d.getQuantity() * d.getMedication().getWeight())
                    .sum();

            if (currentGr < dr.getWeightLimit()) {
                availableDronesList.add(AvailableDronesDto.builder()
                        .serialNumber(dr.getSerialNumber())
                        .weightLimit(dr.getWeightLimit())
                        .freeWeight(dr.getWeightLimit() - currentGr)
                        .build());
            }
        }

        return availableDronesList;
    }

    @Override
    public Integer findDroneBatteryLevel(String serialNumber) {
        if (serialNumber.isEmpty()) {
            throw new BusinessException("The serial number is empty, please send it.");
        }

        Optional<Drone> oDrone = droneRepository.findBySerialNumber(serialNumber);
        return oDrone.orElseThrow(
                        () -> new BusinessException("There is no drone with this serial number, please check."))
                .getBatteryCapacity();
    }

}
