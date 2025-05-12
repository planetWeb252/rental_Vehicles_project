package com.rental.vehicles.controller.routePublic.VehicleController;

import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import com.rental.vehicles.services.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/allVehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
         return vehicleService.allVehicles();
    }


}
