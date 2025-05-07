package com.rental.vehicles.models.DTO.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTOResponseJWT {
    private String name;
    private String token;

}
