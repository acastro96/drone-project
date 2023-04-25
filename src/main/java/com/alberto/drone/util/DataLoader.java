package com.alberto.drone.util;

import com.alberto.drone.repository.entity.Drone;
import com.alberto.drone.repository.entity.Medication;
import com.alberto.drone.repository.entity.DroneMedication;
import com.alberto.drone.repository.jpa.DroneJpa;
import com.alberto.drone.repository.jpa.DroneMedicationJpa;
import com.alberto.drone.repository.jpa.MedicationJpa;
import com.alberto.drone.util.enums.EnumStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private DroneJpa droneJpa;
    @Autowired
    private MedicationJpa medicationJpa;
    @Autowired
    private DroneMedicationJpa droneMedicationJpa;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Drone drone = Drone.builder().serialNumber("123")
                .model("Lightweight")
                .weightLimit(300)
                .batteryCapacity(80)
                .state("IDLE").build();

        Drone drone2 = Drone.builder().serialNumber("456")
                .model("Lightweight")
                .weightLimit(400)
                .batteryCapacity(70)
                .state("LOADING").build();

        Medication medication = Medication.builder()
                .name("Ibuprophen")
                .weight(50)
                .code("123")
                .image("image").build();

        Drone droneSaved = droneJpa.save(drone);
        droneJpa.save(drone2);
        Medication medicationSaved = medicationJpa.save(medication);

        DroneMedication droneMedication = DroneMedication.builder()
                .drone(droneSaved)
                .medication(medicationSaved)
                .quantity(2)
                .state("A").build();

        DroneMedication droneMedicationSaved = droneMedicationJpa.save(droneMedication);

        Drone droneFind = droneJpa.findById(droneSaved.getId()).get();

        //System.out.println(droneSaved);
        System.out.println(medicationSaved);
        //System.out.println(droneMedicationSaved);
        System.out.println(droneFind);
        System.out.println(droneFind.getDroneMedicationLoads());
        System.out.println("===============");

        List<Drone> droneList = droneJpa.findByStates(Arrays.asList(EnumStates.LOADING.value, EnumStates.IDLE.value));
        droneList.forEach(System.out::println);
        //System.out.println(droneFind.getDroneMedicationLoads().stream().findFirst().get().getMedication().getWeight());
    }
}
