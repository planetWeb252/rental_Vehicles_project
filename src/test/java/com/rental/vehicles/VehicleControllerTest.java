package com.rental.vehicles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rental.vehicles.DTO.vehicles.DeleteVehicleDto;
import com.rental.vehicles.DTO.vehicles.UpdateVehiclesDto;
import com.rental.vehicles.DTO.vehicles.VehicleDTO;
import com.rental.vehicles.enums.Enum_Vehicles;
import com.rental.vehicles.enums.Role_Employee;
import com.rental.vehicles.models.modelsClass.User.Employee;
import com.rental.vehicles.models.modelsClass.Vehicles.Car;
import com.rental.vehicles.repositories.EmployeeRepository;
import com.rental.vehicles.repositories.VehicleRepository;
import com.rental.vehicles.services.VehicleService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.rental.vehicles.models.modelsAbstrac.Vehicle;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VehicleControllerTest {


    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private final String LICENSE_PLATE = "8569BNM";

    @Test
    @DisplayName("Create Vehicle should return 201")
    @Order(1)
    void testCreateVehicle_Car_Success() throws Exception {
       VehicleDTO dto = new VehicleDTO();
        dto.setEmailEmployee("laura.garcia2@example.com");
        dto.setTypeOfVehicle("CAR");
        dto.setBrand("Toyota");
        dto.setModel("Corolla");
        dto.setColor("Red");
        dto.setYear(2022);
        dto.setPricePerDay(60.0);
        dto.setFuelType("Gasoline");
        dto.setQuantity(5);
        dto.setStatus_vehicle(Enum_Vehicles.ROLE_AVAILABLE);
        dto.setLicensePlate(LICENSE_PLATE);
        //EMPLOYEE
        Employee employee = new Employee();
        employee.setEmail("laura.garcia2@example.com");
        employee.setRoleEmployee(Role_Employee.ROLE_ADMIN);
        //Car
        Car car = new Car();
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setLicensePlate(dto.getLicensePlate());
        vehicleRepository.save(car);
    }
    @Test
    @Order(2)
    void testUpdateVehicle_Success() throws Exception {


        UpdateVehiclesDto updateDto = new UpdateVehiclesDto();
        updateDto.setEmail("laura.garcia2@example.com");
        updateDto.setBrand("Nissan");
        updateDto.setModel("Altima");
        updateDto.setLicensePlate(LICENSE_PLATE);
        updateDto.setColor("Blue");
        updateDto.setYear(2023);
        updateDto.setPricePerDay(70.0);
        updateDto.setFuelType("Diesel");
        updateDto.setQuantity(3);
        updateDto.setStatus_vehicle("ROLE_AVAILABLE");

        // Create a vehicle to update
        ResponseEntity<?> response = vehicleService.updateVehicle(updateDto);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        Vehicle updated = (Vehicle) response.getBody();
        Assertions.assertNotNull(updated);
        assertThat(updated.getBrand()).isEqualTo("Nissan");
    }

    @Test
    @Order(3)
    void testDeleteVehicle_Success() throws Exception {
        String licensePlate = LICENSE_PLATE;
        DeleteVehicleDto deleteDto = new DeleteVehicleDto();
        deleteDto.setEmail("laura.garcia2@example.com");
        ResponseEntity<?> response = vehicleService.deleteVehicle(licensePlate, deleteDto);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(vehicleRepository.findByLicensePlate(licensePlate)).isEmpty();
    }
}