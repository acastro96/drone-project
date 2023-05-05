package com.alberto.drone.service;

import com.alberto.drone.exception.BusinessException;
import com.alberto.drone.repository.contract.IMedicationRepository;
import com.alberto.drone.repository.entity.Medication;
import com.alberto.drone.service.contract.IMedicationService;
import com.alberto.drone.service.dto.MedicationDto;
import com.alberto.drone.util.mapper.MedicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@Service
public class MedicationService implements IMedicationService {

    @Autowired
    private IMedicationRepository medicationRepository;

    @Autowired
    private MedicationMapper medicationMapper;

    @Value("${images.path}")
    private String imagesPath;

    @Override
    public MedicationDto save(MedicationDto medicationDto, MultipartFile image) {

        if (image.isEmpty()) {
            throw new BusinessException("The image must be send");
        }

        Optional<Medication> oMedication = medicationRepository.findByCode(medicationDto.getCode());
        if (oMedication.isPresent()) {
            throw new BusinessException("This code is already register, please check and try again.");
        }

        //This should be a File Storage Cloud tool where you can storage the image and get the url
        if (!new File(imagesPath).exists()) {
            new File(imagesPath).mkdir();
        }

        String imageFile = imagesPath.concat(medicationDto.getName())
                .concat("_")
                .concat(medicationDto.getCode())
                .concat(".jpg");

        File destination = new File(imageFile);

        try (OutputStream os = new FileOutputStream(destination)) {
            os.write(image.getBytes());
        } catch (IOException e) {
            throw new BusinessException("There was a problem saving the medication image, try again later.");
        }

        medicationDto.setImage(imageFile);
        return medicationMapper.toDto(medicationRepository.save(medicationMapper.toEntity(medicationDto)));
    }

    @Override
    public MedicationDto findByCode(String code) {
        if (code.isEmpty() || code.isBlank()) {
            throw new BusinessException("The medication code cant be null or empty, please check and try again.");
        }

        Optional<Medication> oMedication = medicationRepository.findByCode(code);

        return medicationMapper.toDto(
                oMedication.orElseThrow(
                        () -> new BusinessException("There is no Medication register with this code," +
                                " please check and try again.")));
    }
}
