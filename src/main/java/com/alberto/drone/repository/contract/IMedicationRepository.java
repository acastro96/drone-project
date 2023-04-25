package com.alberto.drone.repository.contract;

import com.alberto.drone.repository.entity.Medication;
import com.alberto.drone.service.dto.MedicationDto;

public interface IMedicationRepository {

    Medication save(Medication medication);

    Medication findById(Long id);

    Medication findByCode(String code);

}
