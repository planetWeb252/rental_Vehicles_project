package com.rental.vehicles.controller.routePublic.VehicleController;

import com.rental.vehicles.DTO.vehicles.DeleteVehicleDto;
import com.rental.vehicles.DTO.vehicles.UpdateVehiclesDto;
import com.rental.vehicles.DTO.vehicles.VehicleDTO;
import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import com.rental.vehicles.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/updateVehicle/{licensePlate}")
    public ResponseEntity<?> updateVehicle(@PathVariable String licensePlate,
                                           @RequestBody @Valid UpdateVehiclesDto dto) {
        dto.setLicensePlate(licensePlate);
       return vehicleService.updateVehicle(dto);
    }
    @PostMapping
    public ResponseEntity<?> createVehicle(@RequestBody @Valid VehicleDTO dto) {
        return vehicleService.createVehicle(dto);
    }
    @DeleteMapping("/deleteVehicle/{licensePlate}")
    public ResponseEntity<?> deleteVehicle(@PathVariable String licensePlate,
                                           @Valid @RequestBody DeleteVehicleDto dto) {
        return vehicleService.deleteVehicle(licensePlate, dto);
    }
}
