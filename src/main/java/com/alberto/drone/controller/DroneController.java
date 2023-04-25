package com.alberto.drone.controller;

import com.alberto.drone.service.contract.IDroneService;
import com.alberto.drone.service.dto.AvailableDronesDto;
import com.alberto.drone.service.dto.DroneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/drone")
public class DroneController {

    @Autowired
    private IDroneService droneService;

    @PostMapping("/add-drone")
    public DroneDto addDrone(@Valid @RequestBody DroneDto droneDto){
        return droneService.saveDrone(droneDto);
    }

    @GetMapping("/get-drone")
    public DroneDto getDrone(@RequestParam("serialNumber") String serialNumber){
        return droneService.findDroneBySerial(serialNumber);
    }

    @GetMapping("/drones-availables")
    public List<AvailableDronesDto> dronesAvailables(){
        return droneService.findAvailableDronesForLoading();
    }

    @GetMapping("/get-battery-level")
    public Integer getBatteryLevel(@RequestParam("serialNumber") String serialNumber){
        return droneService.findDroneBatteryLevel(serialNumber);
    }

}
