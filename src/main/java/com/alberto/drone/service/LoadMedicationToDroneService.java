package com.alberto.drone.service;

import com.alberto.drone.repository.entity.Drone;
import com.alberto.drone.service.contract.ILoadMedicatioToDrone;
import com.alberto.drone.service.dto.DroneDto;
import com.alberto.drone.service.dto.MedicationToLoadDto;
import com.alberto.drone.repository.contract.IDroneRepository;
import com.alberto.drone.repository.contract.IMedicationRepository;
import com.alberto.drone.repository.entity.DroneMedication;
import com.alberto.drone.service.dto.LoadMedicationDto;
import com.alberto.drone.service.dto.MedicationDto;
import com.alberto.drone.util.mapper.DroneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoadMedicationToDroneService implements ILoadMedicatioToDrone {

    @Autowired
    private IDroneRepository droneRepository;

    @Autowired
    private DroneMapper droneMapper;

    @Autowired
    private IMedicationRepository medicationRepository;

    @Override
    public Object loadDrone(LoadMedicationDto loadMedicationDto) {
        int addingGr;

        Drone drone = droneRepository.findBySerialNumber(loadMedicationDto.getDroneSerialNumber());
        DroneDto droneDto = droneMapper.toDto(drone);

        List<DroneMedication> droneMedicationList = new ArrayList<>();

        int currentGr = drone.getDroneMedicationLoads().stream()
                .filter(d -> d.getState().equals("A"))
                .mapToInt(d -> d.getQuantity() * d.getMedication().getWeight())
                .sum();

        for(MedicationToLoadDto m : loadMedicationDto.getMedications()){
            MedicationDto medicationDto = medicationRepository.findByCode(m.getMedicationCode());
            addingGr = medicationDto.getWeight() * m.getQuantity();

            if (currentGr + addingGr > droneDto.getWeightLimit()) {
                break;
            }



            //droneMedicationList.add(DroneMedication.builder().drone())

        }

        addingGr = loadMedicationDto.getMedications().stream().mapToInt(m -> {
            MedicationDto medicationDto = medicationRepository.findByCode(m.getMedicationCode());
            return medicationDto.getWeight() * m.getQuantity();
        }).sum();

        if (currentGr + addingGr > droneDto.getWeightLimit()) {
            throw new RuntimeException();
        }

        return null;


    }

}
