package com.rental.vehicles.services.vehicle;

import com.rental.vehicles.DTO.vehicles.UpdateVehiclesDto;
import com.rental.vehicles.enums.Enum_Vehicles;
import com.rental.vehicles.exceptions.Errormessages;
import com.rental.vehicles.exceptions.Vehicle.VehicleExceptions;
import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import com.rental.vehicles.models.modelsClass.User.Employee;
import com.rental.vehicles.repositories.EmployeeRepository;
import com.rental.vehicles.repositories.VehicleRepositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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

    public ResponseEntity<List<Vehicle>> allVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
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

}