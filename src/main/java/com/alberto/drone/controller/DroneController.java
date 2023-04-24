package com.alberto.drone.controller;

import com.alberto.drone.service.dto.DroneDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DroneController {

    @PostMapping("addDrone")
    public Object addDrone(@Valid @RequestBody DroneDto droneDto){
        return null;
    }

}
