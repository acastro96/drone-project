package com.alberto.drone.repository;

import com.alberto.drone.exception.BusinessException;
import com.alberto.drone.repository.contract.IMedicationRepository;
import com.alberto.drone.repository.entity.Medication;
import com.alberto.drone.repository.jpa.MedicationJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MedicationRepository implements IMedicationRepository {

    @Autowired
    private MedicationJpa medicationJpa;

    @Override
    public Medication save(Medication medication) {
        return medicationJpa.save(medication);
    }

    @Override
    public Medication findById(Long id) {
        return medicationJpa.findById(id)
                .orElseThrow(() -> new BusinessException("There is no medication with this id, please check."));
    }

    @Override
    public Medication findByCode(String code) {
        return medicationJpa.findByCode(code)
                .orElseThrow(() -> new BusinessException("There is no medication with this code, please check."));
    }
}
