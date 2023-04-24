package com.alberto.drone.service.contract;

import com.alberto.drone.service.dto.LoadMedicationDto;

public interface ILoadMedicatioToDrone {

    Object loadDrone(LoadMedicationDto loadMedicationDto);
}
