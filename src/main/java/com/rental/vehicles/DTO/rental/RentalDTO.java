package com.rental.vehicles.DTO.rental;

import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.models.modelsClass.User.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDTO {
    @Email(message = "The email is not valid")
    private String email;
   @NotNull(message = "The start date cannot be null")
    private LocalDate startDate;
    @NotNull(message = "The end date cannot be null")
    private LocalDate endDate;

}
