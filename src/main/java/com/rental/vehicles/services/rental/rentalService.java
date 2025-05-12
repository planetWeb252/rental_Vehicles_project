package com.rental.vehicles.services.rental;

import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.repositories.CustomerRepository;
import com.rental.vehicles.repositories.EmployeeRepository;
import com.rental.vehicles.repositories.RentalRepository;
import com.rental.vehicles.repositories.VehicleRepositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class rentalService {
    private final RentalRepository rentalRepository;
    private  final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    @Autowired
    public rentalService(RentalRepository rentalRepository, VehicleRepository vehicleRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }


    //customer requests rental
//    public ResponseEntity<?> createRental(Long customerId, Long vehicleId, LocalDate startDate, LocalDate endDate) {
//       Optional<Customer> customer = customerRepository.findById(customerId);
//       Optional<Vehicle> optionalVehicle
//
//
//    }






}
