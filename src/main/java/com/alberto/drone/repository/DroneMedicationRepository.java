package com.alberto.drone.repository;

import com.alberto.drone.util.mapper.DroneMedicationMapper;
import com.alberto.drone.repository.contract.IDroneMedicationRepository;
import com.alberto.drone.repository.jpa.DroneMedicationJpa;
import com.alberto.drone.service.dto.DroneMedicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DroneMedicationRepository implements IDroneMedicationRepository {

    @Autowired
    private DroneMedicationJpa droneMedicationJpa;

    @Autowired
    private DroneMedicationMapper droneMedicationMapper;

    /**
     * get all active the medications in one drone by drone ID
     *
     * @param droneId
     * @return
     */
    @Override
    public List<DroneMedicationDto> findByDrone(Long droneId) {
        return droneMedicationJpa.findByDroneId(droneId).stream()
                .map(droneMedicationMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * find all active medications inside all the drones by medication ID
     *
     * @param medId
     * @return
     */
    @Override
    public List<DroneMedicationDto> findByMedication(Long medId) {
        return droneMedicationJpa.findByMedId(medId).stream()
                .map(droneMedicationMapper::toDto)
                .collect(Collectors.toList());
    }
}
