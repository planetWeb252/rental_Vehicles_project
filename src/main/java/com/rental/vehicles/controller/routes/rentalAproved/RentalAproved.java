package com.rental.vehicles.controller.routes.rentalAproved;

import com.rental.vehicles.DTO.rental.RentalDTOAproved;
import com.rental.vehicles.services.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class RentalAproved {
    @Autowired
    private RentalService rentalService;
    @PutMapping("/approve/{rentalId}")
    public ResponseEntity<?> approveRental(@RequestBody @Valid RentalDTOAproved dto, @PathVariable Long rentalId) {
        return rentalService.approveRental(dto,rentalId);
    }


}
