package com.alberto.drone.repository.jpa;

import com.alberto.drone.repository.entity.Drone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DroneJpa extends CrudRepository<Drone, Long> {

    Optional<Drone> findBySerialNumber(String serialNumber);

    List<Drone> findByState(String state);
}
