package com.rental.vehicles.services;

import com.rental.vehicles.DTO.rental.RentalDTO;
import com.rental.vehicles.DTO.rental.RentalDTOAproved;
import com.rental.vehicles.DTO.rental.RentalDTOResponse;
import com.rental.vehicles.enums.RentalStatus;
import com.rental.vehicles.exceptions.Employee.EmployeeExceptions;
import com.rental.vehicles.exceptions.Errormessages;
import com.rental.vehicles.exceptions.Rental.RentalException;
import com.rental.vehicles.exceptions.Vehicle.VehicleExceptions;
import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import com.rental.vehicles.models.modelsClass.Rental;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.models.modelsClass.User.Employee;
import com.rental.vehicles.repositories.CustomerRepository;
import com.rental.vehicles.repositories.EmployeeRepository;
import com.rental.vehicles.repositories.RentalRepository;
import com.rental.vehicles.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private  RentalRepository rentalRepository;
    @Autowired
    private  VehicleRepository vehicleRepository;
    @Autowired
    private  CustomerRepository customerRepository;
    @Autowired
    private  EmployeeRepository employeeRepository;
    @Autowired
    private  VehicleService vehicleService;





    //customer requests rental
    public ResponseEntity<?> createRental(RentalDTO dto, String licensePlate) {
        if (dto.getStartDate().isBefore(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new VehicleExceptions(Errormessages.INVALID_VEHICLE_RENTAL_DATE));
        }
        // customer request
        Optional<Customer> customer = customerRepository.findRoleCustomerByEmail(dto.getEmail());
        if (customer.isPresent()) {
            if (customer.get().getRoleCustomer().name().equals("LOGIN")) {
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


    // employee approve rental
    public ResponseEntity<?> approveRental(RentalDTOAproved dto,Long rentalId) {
        // vehicle is update??
        boolean updateVehicle = false;
        // find in the bbdd rental with status is pending
        List<Rental> rental = rentalRepository.findRentalByStatus(RentalStatus.PENDING);
        if (rental.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RentalException(Errormessages.INVALID_RENTAL));
        }
        // check if employeed is admin
        Optional<Employee> employeeOptional = employeeRepository.findRoleEmployeeByEmail((dto.getEmployeeEmail()));
        if (!employeeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new EmployeeExceptions(Errormessages.INVALID_EMPLOYEE_NOT_FOUND));
        }

        Employee employee = employeeOptional.get();

        // employee is admin??
        if (!employee.getRoleEmployee().name().equals("ADMIN")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RentalException(Errormessages.INVALID_RENTAL_NOT_APPROVED));
        }
        // passed the validations
        Rental rentalbyId = rentalRepository.findRentalById(rentalId);
        //Update Rental
        if (rentalbyId != null) {
            rentalbyId.setAproved(true);
            rentalbyId.setAprovedBy(employee);
            rentalbyId.setAprovedDate(LocalDateTime.now());
            rentalbyId.setStatus(RentalStatus.APPROVED);
            rentalRepository.save(rentalbyId);
            // update quatity vehicle
            updateVehicle = vehicleService.updateVehicleQuantity(dto.getLicensePlate());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RentalException(Errormessages.IVALID_RENTAL_NOT_FOUND));
        }

        if (updateVehicle) {
            return ResponseEntity.status(HttpStatus.OK).body("Rental approved");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new VehicleExceptions(Errormessages.VEHICLE_NOT_FOUND));
        }
    }

    public ResponseEntity<List<Rental>> allRentals() {
       List<Rental> rentals= rentalRepository.findAll();
       return ResponseEntity.ok(rentals);
    }
}




