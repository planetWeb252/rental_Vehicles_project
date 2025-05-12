package com.rental.vehicles.DTO.vehicles;

import com.rental.vehicles.enums.Enum_Vehicles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehiclesDto {


    private String brand;
    private String model;
    @NotNull(message = "The license plate cannot be empty")
    private String licensePlate;
    private String color;
    private int year;
    private double pricePerDay;
    private String fuelType;
    private int quantity;
    private String status_vehicle;
    @NotNull(message = "The email employee cannot be empty")
    @Email(message = "Email should be valid")
    private String email;



}
