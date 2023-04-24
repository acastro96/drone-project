package com.alberto.drone.repository.jpa;

import com.alberto.drone.repository.entity.Medication;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MedicationJpa extends CrudRepository<Medication,Long> {

    Optional<Medication> findByCode(String code);
}
