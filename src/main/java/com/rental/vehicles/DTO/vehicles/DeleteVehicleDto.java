package com.rental.vehicles.DTO.vehicles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteVehicleDto {

    @NotNull(message="email is required")
    @Email(message="email is not valid")
    private String email;
}
