package com.alberto.drone.service;

import com.alberto.drone.exception.BusinessException;
import com.alberto.drone.repository.contract.IDroneMedicationRepository;
import com.alberto.drone.repository.contract.IDroneRepository;
import com.alberto.drone.repository.contract.IMedicationRepository;
import com.alberto.drone.repository.entity.Drone;
import com.alberto.drone.repository.entity.DroneMedication;
import com.alberto.drone.repository.entity.Medication;
import com.alberto.drone.service.contract.IDroneMedicationService;
import com.alberto.drone.service.dto.DroneDto;
import com.alberto.drone.service.dto.LoadMedicationDto;
import com.alberto.drone.service.dto.MedicationToLoadDto;
import com.alberto.drone.util.enums.EnumStates;
import com.alberto.drone.util.mapper.DroneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DroneMedicationService implements IDroneMedicationService {

    @Autowired
    private IDroneRepository droneRepository;

    @Autowired
    private DroneMapper droneMapper;

    @Autowired
    private IMedicationRepository medicationRepository;

    @Autowired
    private IDroneMedicationRepository droneMedicationRepository;

    @Override
    public LoadMedicationDto loadDrone(LoadMedicationDto loadMedicationDto) {
        int addingGr;

        Optional<Drone> oDrone = droneRepository.findBySerialNumber(loadMedicationDto.getDroneSerialNumber());

        Drone drone = oDrone.orElseThrow(() -> new BusinessException("There is no drone registered with this " +
                "serial number, please check and try again"));

        List<MedicationToLoadDto> medicationToLoadDtoListResponse = new ArrayList<>();

        List<DroneMedication> droneMedicationList = new ArrayList<>();

        if (drone.getBatteryCapacity() < 25) {
            throw new BusinessException("The drone has low battery level, and is charging, please wait");
        }

        if (!isReadyToLoad(drone.getState())) {
            throw new BusinessException("The drone is not prepared to load medications State: ".concat(drone.getState()));
        }

        int currentGr = drone.getDroneMedicationLoads().stream()
                .filter(d -> d.getState().equals("A"))
                .mapToInt(d -> d.getQuantity() * d.getMedication().getWeight())
                .sum();

        for (MedicationToLoadDto m : loadMedicationDto.getMedications()) {
            Optional<Medication> oMedication = medicationRepository.findByCode(m.getMedicationCode());

            Medication medication = oMedication.orElseThrow(
                    ()-> new BusinessException("There was an error processing the medication with code "
                            .concat(m.getMedicationCode())
                            .concat(" please check and try again")));

            addingGr = medication.getWeight() * m.getQuantity();

            if (currentGr + addingGr > drone.getWeightLimit()) {
                if (droneMedicationList.isEmpty()) {
                    throw new BusinessException("The medication that has been sent, exceed the drone weight limit");
                }
                break;
            }

            Optional<DroneMedication> droneMedication = drone.getDroneMedicationLoads().stream()
                    .filter(dm -> isMedicationLoaded(dm, m.getMedicationCode()))
                    .findFirst();

            if (droneMedication.isEmpty()) {
                droneMedicationList.add(DroneMedication.builder()
                        .medication(medication)
                        .drone(drone)
                        .quantity(m.getQuantity())
                        .state("A").build());
            } else {
                droneMedicationList.add(DroneMedication.builder()
                        .id(droneMedication.get().getId())
                        .medication(medication)
                        .drone(drone)
                        .quantity(droneMedication.get().getQuantity() + m.getQuantity())
                        .state("A").build());
            }

            medicationToLoadDtoListResponse.add(m);

            currentGr += addingGr;
        }

        if (!drone.getState().equals(EnumStates.LOADING.value)) {
            drone.setState(EnumStates.LOADING.value);
            droneRepository.save(drone);
        }

        droneMedicationRepository.saveAll(droneMedicationList);

        return LoadMedicationDto.builder()
                .droneSerialNumber(drone.getSerialNumber())
                .medications(medicationToLoadDtoListResponse)
                .build();

    }

    @Override
    public DroneDto startDelivering(String serialNumber) {
        Optional<Drone> oDrone = droneRepository.findBySerialNumber(serialNumber);

        Drone drone = oDrone.orElseThrow(
                () -> new BusinessException("There is no drone with this serial number, please check."));

        drone.setState(EnumStates.DELIVERING.value);
        return droneMapper.toDto(droneRepository.save(drone));
    }

    @Override
    public DroneDto finishDelivering(String serialNumber) {
        Optional<Drone> oDrone = droneRepository.findBySerialNumber(serialNumber);

        Drone drone = oDrone.orElseThrow(
                () -> new BusinessException("There is no drone with this serial number, please check."));

        drone.getDroneMedicationLoads().forEach(d -> d.setState("D"));
        droneMedicationRepository.saveAll(drone.getDroneMedicationLoads());

        drone.setState(EnumStates.DELIVERED.value);
        return droneMapper.toDto(droneRepository.save(drone));
    }

    @Override
    public DroneDto returnDrone(String serialNumber) {
        Optional<Drone> oDrone = droneRepository.findBySerialNumber(serialNumber);

        Drone drone = oDrone.orElseThrow(
                () -> new BusinessException("There is no drone with this serial number, please check."));

        drone.setState(EnumStates.RETURNING.value);
        return droneMapper.toDto(droneRepository.save(drone));
    }

    @Override
    public DroneDto droneArrived(String serialNumber) {
        Optional<Drone> oDrone = droneRepository.findBySerialNumber(serialNumber);

        Drone drone = oDrone.orElseThrow(
                () -> new BusinessException("There is no drone with this serial number, please check."));

        drone.setState(EnumStates.IDLE.value);
        return droneMapper.toDto(droneRepository.save(drone));
    }

    private Boolean isMedicationLoaded(DroneMedication droneMedication, String codeMedication) {
        return droneMedication.getMedication().getCode().equals(codeMedication)
                && droneMedication.getState().equals("A");
    }

    private Boolean isReadyToLoad(String state) {
        return ((state.equals(EnumStates.IDLE.value)
                || state.equals(EnumStates.LOADING.value))
                && !state.equals(EnumStates.LOADED.value));
    }

}
