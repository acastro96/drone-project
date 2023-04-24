package com.alberto.drone.repository;

import com.alberto.drone.repository.entity.Medication;
import com.alberto.drone.repository.contract.IMedicationRepository;
import com.alberto.drone.repository.jpa.MedicationJpa;
import com.alberto.drone.service.dto.MedicationDto;
import com.alberto.drone.util.mapper.MedicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MedicationRepository implements IMedicationRepository {

    @Autowired
    private MedicationJpa medicationJpa;

    @Autowired
    private MedicationMapper medicationMapper;

    @Override
    public MedicationDto save(Medication medication) {
        return medicationMapper.toDto(medicationJpa.save(medication));
    }

    @Override
    public MedicationDto findById(Long id) {
        return medicationJpa.findById(id)
                .map(medicationMapper::toDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public MedicationDto findByCode(String code) {
        return medicationJpa.findByCode(code)
                .map(medicationMapper::toDto)
                .orElseThrow(RuntimeException::new);
    }
}
