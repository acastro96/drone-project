package com.alberto.drone.repository;

import com.alberto.drone.exception.BusinessException;
import com.alberto.drone.repository.contract.IDroneRepository;
import com.alberto.drone.repository.entity.Drone;
import com.alberto.drone.repository.jpa.DroneJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DroneRepository implements IDroneRepository {

    @Autowired
    private DroneJpa droneJpa;

    @Override
    public Drone save(Drone drone) {
        return droneJpa.save(drone);
    }

    @Override
    public List<Drone> saveAll(List<Drone> droneList) {
        return (List<Drone>) droneJpa.saveAll(droneList);
    }

    @Override
    public Drone findById(Long id) {
        return droneJpa.findById(id)
                .orElseThrow(() -> new BusinessException("There is no drone with this id, please check."));
    }

    @Override
    public Drone findBySerialNumber(String serialNumber) {
        return droneJpa.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new BusinessException("There is no drone with this serial number, please check."));
    }

    @Override
    public List<Drone> findByStates(List<String> states) {
        return droneJpa.findByStates(states);
    }

    @Override
    public List<Drone> findAll() {
        return (List<Drone>) droneJpa.findAll();
    }
}
