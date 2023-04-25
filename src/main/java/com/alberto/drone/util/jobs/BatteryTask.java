package com.alberto.drone.util.jobs;

import com.alberto.drone.repository.contract.IDroneRepository;
import com.alberto.drone.repository.entity.Drone;
import com.alberto.drone.util.enums.EnumStates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatteryTask {

    Logger logger = LoggerFactory.getLogger(BatteryTask.class);

    @Autowired
    private IDroneRepository droneRepository;

    @Scheduled(fixedDelay = 300000)
    public void checkingBattery() {
        List<Drone> droneList = droneRepository.findAll();

        droneList.forEach(d -> {
            if (d.getState().equals(EnumStates.IDLE.value)) {
                if(d.getBatteryCapacity()<=90){
                    d.setBatteryCapacity(d.getBatteryCapacity() + 10);
                }
            }else{
                if(d.getBatteryCapacity()>=5){
                    d.setBatteryCapacity(d.getBatteryCapacity() - 5);
                }

                if(d.getBatteryCapacity()<25 && d.getState().equals(EnumStates.LOADING.value)){
                    d.setState(EnumStates.IDLE.value);
                }
            }

            logger.info("Drone serial: ".concat(d.getSerialNumber())
                    .concat(" battery: ").concat(d.getBatteryCapacity()+"")
                    .concat(" state: ").concat(d.getState()));
        });

        droneRepository.saveAll(droneList);
    }

}
