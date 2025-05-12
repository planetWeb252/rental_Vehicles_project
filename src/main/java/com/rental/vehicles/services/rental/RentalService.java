package com.rental.vehicles.services.rental;

import com.rental.vehicles.DTO.rental.RentalDTO;
import com.rental.vehicles.DTO.rental.RentalDTOResponse;
import com.rental.vehicles.enums.RentalStatus;
import com.rental.vehicles.exceptions.Errormessages;
import com.rental.vehicles.exceptions.Vehicle.VehicleExceptions;
import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import com.rental.vehicles.models.modelsClass.Rental;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.repositories.CustomerRepository;
import com.rental.vehicles.repositories.EmployeeRepository;
import com.rental.vehicles.repositories.RentalRepository;
import com.rental.vehicles.repositories.VehicleRepositories.VehicleRepository;
import com.rental.vehicles.services.jwtServices.JwtServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final JwtServices jwtService;

    @Autowired
    public RentalService(RentalRepository rentalRepository, VehicleRepository vehicleRepository,
                         CustomerRepository customerRepository, EmployeeRepository employeeRepository, JwtServices jwtService) {
        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.jwtService = jwtService;
    }


    //customer requests rental
    public ResponseEntity<?> createRental(RentalDTO dto, String licensePlate) {
        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new VehicleExceptions(Errormessages.INVALID_VEHICLE_RENTAL_DATE));
        }
        // customer request
        Optional<Customer> customer = customerRepository.findRoleCustomerByEmail(dto.getEmail());
        if (customer.isPresent()) {
            if (customer.get().getRoleCustomer().name().equals("ROLE_LOGIN")) {
                // check if vehicle is available
                Optional<Vehicle> vehicle = vehicleRepository.findByLicensePlate(licensePlate);
                if (vehicle.isPresent()) {
                    if (vehicle.get().isAvailable()) {
                        // create rental
                        Rental rental = new Rental();
                        rental.setCustomer(customer.get());
                        rental.setVehicle(vehicle.get());
                        rental.setStartDate(LocalDate.now());
                        rental.setEndDate(dto.getEndDate());
                        rental.setStatus(RentalStatus.PENDING);
                        // save Vehicle-Rental
                        rentalRepository.save(rental);
                        // update vehicle status
                        vehicle.get().setAvailable(false);
                        vehicleRepository.save(vehicle.get());
                        RentalDTOResponse rentalDTOResponse = new RentalDTOResponse();
                        rentalDTOResponse.setBrand(vehicle.get().getBrand());
                        rentalDTOResponse.setModel(vehicle.get().getModel());
                        rentalDTOResponse.setLicensePlate(vehicle.get().getLicensePlate());
                        rentalDTOResponse.setAvailable("Pending");
                        return ResponseEntity.status(HttpStatus.OK).body(rentalDTOResponse);
                    } else {
                        return ResponseEntity.badRequest().body(new VehicleExceptions(Errormessages.INVALID_VEHICLE));
                    }
                } else {
                    return ResponseEntity.badRequest().body(new VehicleExceptions(Errormessages.VEHICLE_NOT_FOUND));
                }
            }
        }

        return ResponseEntity.badRequest().body("Customer not Login");

    }


}
