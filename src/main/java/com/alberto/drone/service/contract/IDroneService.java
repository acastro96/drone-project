package com.alberto.drone.service.contract;

import com.alberto.drone.service.dto.AvailableDronesDto;
import com.alberto.drone.service.dto.DroneDto;

import java.util.List;

public interface IDroneService {

    DroneDto saveDrone(DroneDto droneDto);

    DroneDto getDrone(String serialNumber);

    List<AvailableDronesDto> getAvailableDronesForLoading();

    Integer getDroneBatteryLevel(String serialNumber);

}
