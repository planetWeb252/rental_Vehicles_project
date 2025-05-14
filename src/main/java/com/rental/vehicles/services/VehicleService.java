package com.rental.vehicles.services;

import com.rental.vehicles.DTO.vehicles.DeleteVehicleDto;
import com.rental.vehicles.DTO.vehicles.UpdateVehiclesDto;
import com.rental.vehicles.DTO.vehicles.VehicleDTO;
import com.rental.vehicles.enums.Enum_Vehicles;
import com.rental.vehicles.exceptions.Errormessages;
import com.rental.vehicles.exceptions.Vehicle.VehicleExceptions;
import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import com.rental.vehicles.models.modelsClass.User.Employee;
import com.rental.vehicles.models.modelsClass.Vehicles.Car;
import com.rental.vehicles.models.modelsClass.Vehicles.Van;
import com.rental.vehicles.repositories.EmployeeRepository;
import com.rental.vehicles.repositories.VehicleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository, EmployeeRepository employeeRepository) {
        this.vehicleRepository = vehicleRepository;
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<?> allVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        if (vehicles.isEmpty()) {
            return  ResponseEntity.ok().body("No Vehicles in the database");
        }
        return ResponseEntity.ok(vehicles);
    }

    public ResponseEntity<?> updateVehicle(UpdateVehiclesDto dto) {

        Optional<Employee> employeeOptional = employeeRepository.findByEmail(dto.getEmail());
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            try {
                if (employee.getRoleEmployee().name().equals("ROLE_ADMIN")) {
                    Vehicle vehicle = vehicleRepository.findByLicensePlate(dto.getLicensePlate()).orElse(null);
                    if (vehicle != null) {

                        vehicle.setBrand(dto.getBrand());
                        vehicle.setModel(dto.getModel());
                        vehicle.setColor(dto.getColor());
                        vehicle.setYear(dto.getYear());
                        vehicle.setPricePerDay(dto.getPricePerDay());
                        vehicle.setFuelType(dto.getFuelType());
                        vehicle.setQuantity(dto.getQuantity());
                        vehicle.setStatus_vehicle(Enum_Vehicles.valueOf(dto.getStatus_vehicle().toUpperCase()));
                        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
                        return ResponseEntity.ok(updatedVehicle);
                    } else {
                        return ResponseEntity.status(404).body(new VehicleExceptions(Errormessages.VEHICLE_NOT_FOUND));
                    }


                } else {
                    return ResponseEntity.status(403).body("You don't have permission to update this vehicle");
                }
            } catch (Exception e) {
                return ResponseEntity.status(403).body("You don't have permission to update this vehicle");
            }
        } else {
            return ResponseEntity.status(404).body("Employee not found");
        }
    }

    public boolean updateVehicleQuantity(String licensePlate) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByLicensePlate(licensePlate);
        if (!vehicleOptional.isPresent()) {
            return false;
        }
        Vehicle vehicle = vehicleOptional.get();
        vehicle.setQuantity(vehicle.getQuantity() - 1);
        return true;
    }

    public ResponseEntity<?> createVehicle(@Valid VehicleDTO dto) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(dto.getEmailEmployee());
        if (!employeeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new VehicleExceptions(Errormessages.EMPLOYEE_NOT_FOUND));
        }
        if (!employeeOptional.get().getRoleEmployee().name().equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new VehicleExceptions(Errormessages.INVALID_EMPLOYEE_NOT_ADMIN));
        }
        String vehicleType = dto.getTypeOfVehicle();
        switch (vehicleType) {
            case "CAR":
                Car car = new Car();
                car.setBrand(dto.getBrand());
                car.setModel(dto.getModel());
                car.setColor(dto.getColor());
                car.setYear(dto.getYear());
                car.setPricePerDay(dto.getPricePerDay());
                car.setFuelType(dto.getFuelType());
                car.setQuantity(dto.getQuantity());
                car.setStatus_vehicle(dto.getStatus_vehicle());
                car.setLicensePlate(dto.getLicensePlate());
                Vehicle savedCar = vehicleRepository.save(car);
                return ResponseEntity.status(201).body(savedCar);
            case "VAN":
                Van van = new Van();
                van.setBrand(dto.getBrand());
                van.setModel(dto.getModel());
                van.setColor(dto.getColor());
                van.setYear(dto.getYear());
                van.setPricePerDay(dto.getPricePerDay());
                van.setFuelType(dto.getFuelType());
                van.setQuantity(dto.getQuantity());
                van.setStatus_vehicle(dto.getStatus_vehicle());
                van.setLicensePlate(dto.getLicensePlate());
                Vehicle savedVan = vehicleRepository.save(van);
                return ResponseEntity.status(201).body(savedVan);
            default:
                return ResponseEntity.status(400).body(new VehicleExceptions(Errormessages.INVALID_TYPE_VEHICLE));
        }
    }

    public ResponseEntity<?> deleteVehicle(String licensePlate, DeleteVehicleDto dto) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(dto.getEmail());
        if (!employeeOptional.isPresent()) {
            return ResponseEntity.status(404).body(new VehicleExceptions(Errormessages.EMPLOYEE_NOT_FOUND));
        }
        if (!employeeOptional.get().getRoleEmployee().name().equals("ROLE_ADMIN")) {
            return ResponseEntity.status(403).body(new VehicleExceptions(Errormessages.INVALID_EMPLOYEE_NOT_ADMIN));
        }
        Optional<Vehicle> vehicleOptional = vehicleRepository.findByLicensePlate(licensePlate);
        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();
            vehicleRepository.delete(vehicle);
            return ResponseEntity.ok("Vehicle deleted successfully");
        } else {
            return ResponseEntity.status(404).body(new VehicleExceptions(Errormessages.VEHICLE_NOT_FOUND));
        }
    }
}