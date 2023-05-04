package com.alberto.drone.repository.contract;

import com.alberto.drone.repository.entity.Drone;

import java.util.List;
import java.util.Optional;


public interface IDroneRepository {

    Drone save(Drone drone);

    List<Drone> saveAll(List<Drone> droneList);

    Optional<Drone> findById(Long id);

    Optional<Drone> findBySerialNumber(String serialNumber);

    List<Drone> findByStates(List<String> states);

    List<Drone> findAll();
}
