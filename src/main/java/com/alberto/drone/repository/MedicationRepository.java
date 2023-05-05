package com.alberto.drone.repository;

import com.alberto.drone.repository.contract.IMedicationRepository;
import com.alberto.drone.repository.entity.Medication;
import com.alberto.drone.repository.jpa.MedicationJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MedicationRepository implements IMedicationRepository {

    @Autowired
    private MedicationJpa medicationJpa;

    @Override
    public Medication save(Medication medication) {
        return medicationJpa.save(medication);
    }

    @Override
    public Optional<Medication> findById(Long id) {
        return medicationJpa.findById(id);
    }

    @Override
    public Optional<Medication> findByCode(String code) {
        return medicationJpa.findByCode(code);
    }
}
