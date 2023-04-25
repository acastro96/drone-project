package com.alberto.drone.service.contract;

import com.alberto.drone.service.dto.DroneDto;
import com.alberto.drone.service.dto.LoadMedicationDto;

public interface IDroneMedicationService {

    LoadMedicationDto loadDrone(LoadMedicationDto loadMedicationDto);

    DroneDto startDelivering(String serialNumber);

    DroneDto finishDelivering(String serialNumber);

    DroneDto returnDrone(String serialNumber);

    DroneDto droneArrived(String serialNumber);


}
