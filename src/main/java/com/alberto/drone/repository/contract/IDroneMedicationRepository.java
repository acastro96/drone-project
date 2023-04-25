package com.alberto.drone.repository.contract;

import com.alberto.drone.repository.entity.DroneMedication;

import java.util.List;

public interface IDroneMedicationRepository {

    /**
     * get all active the medications in one drone by drone ID
     *
     * @param droneId drone Id
     * @return list of DroneMedication objects in active state with the given drone Id
     */
    List<DroneMedication> findByDrone(Long droneId);

    /**
     * find all active medications inside all the drones by medication ID
     *
     * @param medId medication Id
     * @return list of DroneMedication objects in active state with the given medication Id
     */
    List<DroneMedication> findByMedication(Long medId);

    /**
     * save/update all the elements inside the list
     *
     * @param droneMedicationList list to save
     * @return list of DroneMedication objects saved
     */
    List<DroneMedication> saveAll(List<DroneMedication> droneMedicationList);
}
