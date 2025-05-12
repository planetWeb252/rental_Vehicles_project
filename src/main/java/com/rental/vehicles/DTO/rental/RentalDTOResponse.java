package com.rental.vehicles.DTO.rental;

import com.rental.vehicles.enums.Enum_Vehicles;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTOResponse {

    private String brand;
    private String model;
    private String licensePlate;
    private String available;
}
