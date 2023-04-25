package com.alberto.test.service;

import com.alberto.drone.exception.BusinessException;
import com.alberto.drone.repository.contract.IDroneRepository;
import com.alberto.drone.repository.entity.Drone;
import com.alberto.drone.service.DroneService;
import com.alberto.drone.service.dto.DroneDto;
import com.alberto.drone.util.mapper.DroneMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DroneServiceTest {

    @Mock
    private IDroneRepository droneRepository;

    @Mock
    private DroneMapper droneMapper;

    @InjectMocks
    private DroneService droneService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    private DroneDto droneDto = DroneDto.builder().serialNumber("serialNumber").build();

    @Test
    void saveExistingTest(){
        Mockito.when(droneRepository.findBySerialNumber(Mockito.anyString())).thenReturn(Drone.builder().build());
        Assertions.assertThrows(BusinessException.class, () -> droneService.saveDrone(droneDto));
    }

    @Test
    void saveCorrectTest(){
        Mockito.when(droneRepository.findBySerialNumber(Mockito.anyString())).thenReturn(null);
        Mockito.when(droneMapper.toDto(Mockito.any())).thenReturn(droneDto);
        Mockito.when(droneMapper.toEntity(Mockito.any())).thenReturn(Drone.builder().build());
        Mockito.when(droneRepository.save(Mockito.any())).thenReturn(Drone.builder().build());

        DroneDto savedDrone = droneService.saveDrone(droneDto);

        Assertions.assertEquals("serialNumber", savedDrone.getSerialNumber());
    }

    @Test
    void findDroneBySerialEmptyTest(){
        Assertions.assertThrows(BusinessException.class, () -> droneService.findDroneBySerial(""));
    }

    @Test
    void findDroneBySerialNullTest(){
        Mockito.when(droneRepository.findBySerialNumber(Mockito.anyString())).thenReturn(null);
        Assertions.assertThrows(BusinessException.class,
                () -> droneService.findDroneBySerial("serialNumber"));
    }

    @Test
    void findDroneBySerialCorrectTest(){
        Mockito.when(droneRepository.findBySerialNumber(Mockito.anyString())).thenReturn(Drone.builder().build());
        Mockito.when(droneMapper.toDto(Mockito.any())).thenReturn(droneDto);

        Assertions.assertEquals("serialNumber",
                droneService.findDroneBySerial("serialNumber").getSerialNumber());
    }

}
