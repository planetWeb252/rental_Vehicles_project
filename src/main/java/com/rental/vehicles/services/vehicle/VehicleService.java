package com.rental.vehicles.services.vehicle;

import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import com.rental.vehicles.repositories.VehicleRepositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public ResponseEntity<List<Vehicle>> allVehicles() {
        List<Vehicle> vehicles= vehicleRepository.findAll();
        return ResponseEntity.ok(vehicles);
    }
}
