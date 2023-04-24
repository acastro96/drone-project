package com.alberto.drone.repository.jpa;

import com.alberto.drone.repository.entity.DroneMedication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface DroneMedicationJpa extends CrudRepository<DroneMedication, Long> {

    @Query(value = "SELECT d FROM Drone_Medication d WHERE d.drone_id = :droneId", nativeQuery = true)
    Collection<DroneMedication> findByDroneId(@Param("droneId") Long droneId);

    @Query(value = "SELECT d FROM Drone_Medication d WHERE d.med_id = :medId", nativeQuery = true)
    Collection<DroneMedication> findByMedId(@Param("medId") Long medId);

}
