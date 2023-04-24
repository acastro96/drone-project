package com.alberto.drone.repository;

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
    public Drone findById(Long id) {
        return droneJpa.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Drone findBySerialNumber(String serialNumber) {
        return droneJpa.findBySerialNumber(serialNumber)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Drone> findByState(String state) {
        return droneJpa.findByState(state);
    }
}
