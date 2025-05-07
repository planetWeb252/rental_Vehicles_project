package com.rental.vehicles.models.DTO;

import com.rental.vehicles.enums.Enum_Vehicles;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    @NotBlank(message = "ID cannot be blank")
    private Long id;
    @NotBlank(message = "Brand cannot be blank")
    private String brand;
    @NotBlank(message = "Model cannot be blank")
    private String model;
    @NotBlank(message = "Color cannot be blank")
    private String color;
    @Digits(integer = 4, fraction = 0, message = "Year must be a valid year")
    private int year;
    @NotBlank(message = "Price per day cannot be blank")
    private double pricePerDay;
    @NotBlank(message = "Fuel type cannot be blank")
    private String fuelType;
    @Digits(integer = 2, fraction = 0, message = "Quantity must be a valid number")
    private int quantity;
    @NotBlank(message = "Status vehicle cannot be blank")
    private Enum_Vehicles status_vehicle;
}
