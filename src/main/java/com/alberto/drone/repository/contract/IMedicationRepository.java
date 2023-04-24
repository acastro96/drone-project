package com.alberto.drone.repository.contract;

import com.alberto.drone.repository.entity.Medication;
import com.alberto.drone.service.dto.MedicationDto;

public interface IMedicationRepository {

    MedicationDto save(Medication medication);

    MedicationDto findById(Long id);

    MedicationDto findByCode(String code);

}
