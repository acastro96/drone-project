package com.alberto.drone.repository;

import com.alberto.drone.repository.contract.IDroneMedicationRepository;
import com.alberto.drone.repository.entity.DroneMedication;
import com.alberto.drone.repository.jpa.DroneMedicationJpa;
import com.alberto.drone.util.mapper.DroneMedicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DroneMedicationRepository implements IDroneMedicationRepository {

    @Autowired
    private DroneMedicationJpa droneMedicationJpa;

    @Autowired
    private DroneMedicationMapper droneMedicationMapper;

    /**
     * get all active the medications in one drone by drone ID
     *
     * @param droneId drone Id
     * @return list of DroneMedication objects in active state with the given drone Id
     */
    @Override
    public List<DroneMedication> findByDrone(Long droneId) {
        return droneMedicationJpa.findByDroneId(droneId);
    }

    /**
     * find all active medications inside all the drones by medication ID
     *
     * @param medId medication Id
     * @return list of DroneMedication objects in active state with the given medication Id
     */
    @Override
    public List<DroneMedication> findByMedication(Long medId) {
        return droneMedicationJpa.findByMedId(medId);
    }

    /**
     * save/update all the elements inside the list
     *
     * @param droneMedicationList list to save
     * @return list of DroneMedication objects saved
     */
    @Override
    public List<DroneMedication> saveAll(List<DroneMedication> droneMedicationList) {
        return (List<DroneMedication>) droneMedicationJpa.saveAll(droneMedicationList);
    }
}
