package com.rental.vehicles.DTO.vehicles;

import com.rental.vehicles.enums.Enum_Vehicles;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class VehicleDTO {

    private Long id;
    @NotNull(message = "Brand cannot be blank")
    private String brand;
    @NotNull(message = "Model cannot be blank")
    private String model;
    @NotNull(message = "Color cannot be blank")
    private String color;
    @Digits(integer = 4, fraction = 0, message = "Year must be a valid year")
    private int year;
    @NotNull(message = "Price per day cannot be blank")
    private double pricePerDay;
    @NotNull(message = "Fuel type cannot be blank")
    private String fuelType;
    @Digits(integer = 2, fraction = 0, message = "Quantity must be a valid number")
    private int quantity;
    @NotNull(message = "Status vehicle cannot be blank")
    private Enum_Vehicles status_vehicle;
    @NotNull(message = "The type of vehicle cannot be null")
    private String typeOfVehicle;
    @NotNull(message = "License plate cannot be blank")
    private String licensePlate;
    @NotNull(message = "Employee email cannot be blank")
    @Email(message = "The email is not valid")
    private String emailEmployee;
}
