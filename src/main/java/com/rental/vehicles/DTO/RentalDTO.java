package com.rental.vehicles.DTO;

import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.models.modelsClass.User.Employee;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDTO {
    @NotBlank(message = "The id cannot be empty")
    private Long id;
    @NotBlank(message = "The start date cannot be empty")
    private LocalDate startDate;
    @NotBlank(message = "The end date cannot be empty")
    private LocalDate endDate;
    @NotBlank(message = "The total price cannot be empty")
    private BigDecimal totalPrice;
    @NotBlank(message = "The aproved cannot be empty")
    private boolean aproved;
    @NotBlank(message = "The returned cannot be empty")
    private boolean returned;
    // relation with vehicle
    private Vehicle vehicles;
    // relation with customer
    private Customer customer;
    // relation with employee
    private Employee aprovedBy;

}
