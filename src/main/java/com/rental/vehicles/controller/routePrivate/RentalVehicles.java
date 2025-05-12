package com.rental.vehicles.controller.routePrivate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private/rental")
public class RentalVehicles {
  //TODO: Implement the rental vehicle functionality
    @PostMapping
    public ResponseEntity<?> creationRental() {
        return ResponseEntity.ok("Has entrado con user logueado");
    }
}
