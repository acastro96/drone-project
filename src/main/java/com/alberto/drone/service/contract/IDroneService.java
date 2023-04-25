package com.alberto.drone.service.contract;

import com.alberto.drone.service.dto.AvailableDronesDto;
import com.alberto.drone.service.dto.DroneDto;

import java.util.List;

public interface IDroneService {

    DroneDto saveDrone(DroneDto droneDto);

    DroneDto findDroneBySerial(String serialNumber);

    List<AvailableDronesDto> findAvailableDronesForLoading();

    Integer findDroneBatteryLevel(String serialNumber);

}
