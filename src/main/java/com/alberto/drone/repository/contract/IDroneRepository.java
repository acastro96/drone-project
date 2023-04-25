package com.alberto.drone.repository.contract;

import com.alberto.drone.repository.entity.Drone;

import java.util.List;


public interface IDroneRepository {

    Drone save(Drone drone);

    List<Drone> saveAll(List<Drone> droneList);

    Drone findById(Long id);

    Drone findBySerialNumber(String serialNumber);

    List<Drone> findByStates(List<String> states);

    List<Drone> findAll();
}
