package com.rental.vehicles.controller.routes.rentalAproved;

import com.rental.vehicles.DTO.rental.RentalDTO;
import com.rental.vehicles.DTO.rental.RentalDTOAproved;
import com.rental.vehicles.services.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/rental")
public class RentalVehicles {
    @Autowired
    private RentalService rentalService;

    //TODO: Implement the rental vehicle functionality
    @PostMapping("/request/{licensePlate}")
    public ResponseEntity<?> creationRental(@RequestBody @Valid RentalDTO dto,@PathVariable String licensePlate) {
       return rentalService.createRental( dto,licensePlate);

    }


}
