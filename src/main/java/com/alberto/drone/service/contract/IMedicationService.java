package com.alberto.drone.service.contract;

import com.alberto.drone.service.dto.MedicationDto;

import java.util.List;

public interface IMedicationService {

    List<MedicationDto> getMedicationByDrone(String droneSerialNumber);

}
