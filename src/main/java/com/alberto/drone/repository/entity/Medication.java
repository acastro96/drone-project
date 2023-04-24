package com.alberto.drone.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "Medication")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "med_id")
    private Long id;
    @Column(name = "med_name")
    private String name;
    @Column(name = "med_weight")
    private int weight;
    @Column(name = "med_code")
    private String code;
    @Column(name = "med_image")
    private String image;

    @OneToMany(mappedBy = "medication")
    @ToString.Exclude
    private Set<DroneMedication> droneMedicationLoads;
}
