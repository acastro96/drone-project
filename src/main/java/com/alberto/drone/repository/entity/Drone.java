package com.alberto.drone.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Drone")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drone_id")
    private Long id;
    @Column(name = "drone_serial_number")
    private String serialNumber;
    @Column(name = "drone_model")
    private String model;
    @Column(name = "drone_weight_limit")
    private int weightLimit;
    @Column(name = "drone_battery_capacity")
    private int batteryCapacity;
    @Column(name = "drone_state")
    private String state;

    @OneToMany(mappedBy = "drone")
    @Fetch(FetchMode.JOIN)
    @ToString.Exclude
    private List<DroneMedication> droneMedicationLoads;

}
