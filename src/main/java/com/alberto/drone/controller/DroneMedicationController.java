package com.alberto.drone.controller;

import com.alberto.drone.service.contract.IDroneMedicationService;
import com.alberto.drone.service.dto.DroneDto;
import com.alberto.drone.service.dto.LoadMedicationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drone-medication")
public class DroneMedicationController {

    @Autowired
    private IDroneMedicationService droneMedicationService;

    @PostMapping("/load-drone")
    public LoadMedicationDto loadMedication(@RequestBody LoadMedicationDto loadMedicationDto){
        return droneMedicationService.loadDrone(loadMedicationDto);
    }

    @PutMapping("/start-delivering")
    public DroneDto startDelivering(@RequestParam("serialNumber") String serialNumber){
        return droneMedicationService.startDelivering(serialNumber);
    }

    @PutMapping("/finish-delivering")
    public DroneDto finishDelivering(@RequestParam("serialNumber") String serialNumber){
        return droneMedicationService.finishDelivering(serialNumber);
    }

    @PutMapping("/return-drone")
    public DroneDto returnDrone(@RequestParam("serialNumber") String serialNumber){
        return droneMedicationService.returnDrone(serialNumber);
    }

    @PutMapping("/drone-arrived")
    public DroneDto droneArrived(@RequestParam("serialNumber") String serialNumber){
        return droneMedicationService.returnDrone(serialNumber);
    }

}
