package com.alberto.drone.repository.contract;

import com.alberto.drone.repository.entity.Medication;

import java.util.Optional;

public interface IMedicationRepository {

    Medication save(Medication medication);

    Optional<Medication> findById(Long id);

    Optional<Medication> findByCode(String code);

}
