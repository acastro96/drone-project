package com.alberto.drone.repository.contract;

import com.alberto.drone.repository.entity.Drone;

import java.util.List;


public interface IDroneRepository {

    Drone save(Drone drone);

    Drone findById(Long id);

    Drone findBySerialNumber(String serialNumber);

    List<Drone> findByState(String state);
}
