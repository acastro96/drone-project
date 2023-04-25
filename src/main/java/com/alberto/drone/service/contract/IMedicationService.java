package com.alberto.drone.service.contract;

import com.alberto.drone.service.dto.MedicationDto;
import org.springframework.web.multipart.MultipartFile;

public interface IMedicationService {

    MedicationDto save(MedicationDto medicationDto, MultipartFile image);

    MedicationDto findByCode(String code);

}
