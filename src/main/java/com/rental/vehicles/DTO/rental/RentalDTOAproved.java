package com.rental.vehicles.DTO.rental;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDTOAproved {

    private Long id;
    private String brand;
    private String model;
    @NotNull(message = "License plate is required")
    private String licensePlate;
    private String startDate;
    private String endDate;
    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid")
    private String employeeEmail;
    private boolean approved;
}
