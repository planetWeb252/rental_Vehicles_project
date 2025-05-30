package com.rental.vehicles.models.modelsAbstrac;

import com.rental.vehicles.enums.Enum_Vehicles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String licensePlate;
    private String color;
    private int year;
    private double pricePerDay;
    private String fuelType;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private Enum_Vehicles status_vehicle;
    @Column(name = "available")
    private boolean available=true;

}
