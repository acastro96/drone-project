package com.alberto.drone.repository.jpa;

import com.alberto.drone.repository.entity.Drone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DroneJpa extends CrudRepository<Drone, Long> {

    Optional<Drone> findBySerialNumber(String serialNumber);

    @Query(value = "SELECT d.* FROM Drone d WHERE d.drone_state in :states", nativeQuery = true)
    List<Drone> findByStates(@Param("states") List<String> state);
}
