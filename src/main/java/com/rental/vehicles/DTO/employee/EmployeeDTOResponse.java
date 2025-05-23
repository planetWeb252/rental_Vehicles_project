package com.rental.vehicles.DTO.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTOResponse {
    private String name;
    private String surname;
    private String email;
}
