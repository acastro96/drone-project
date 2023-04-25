package com.alberto.drone.controller;

import com.alberto.drone.service.contract.IMedicationService;
import com.alberto.drone.service.dto.MedicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController()
@RequestMapping("/medication")
public class MedicationController {

    @Autowired
    private IMedicationService medicationService;

    @PostMapping("/add-medication")
    public MedicationDto addMedication(@Valid @RequestPart("medication") MedicationDto medicationDto,
                                       @RequestPart("image") MultipartFile image){
        return medicationService.save(medicationDto, image);
    }

    @GetMapping("/get-medication")
    public MedicationDto getMedication(@RequestParam("code") String code){
        return medicationService.findByCode(code);
    }

}
