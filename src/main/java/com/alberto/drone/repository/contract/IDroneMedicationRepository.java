package com.alberto.drone.repository.contract;

import com.alberto.drone.service.dto.DroneMedicationDto;

import java.util.List;

public interface IDroneMedicationRepository {

    /**
     * get all active the medications in one drone by drone ID
     * @param droneId
     * @return
     */
    List<DroneMedicationDto> findByDrone(Long droneId);

    /**
     * find all active medications inside all the drones by medication ID
     * @param medId
     * @return
     */
    List<DroneMedicationDto> findByMedication(Long medId);
}
