package com.rental.vehicles.Employee;

import com.rental.vehicles.DTO.employee.EmployeeDTORegister;
import com.rental.vehicles.enums.Role_Employee;
import com.rental.vehicles.services.User.EmployeeService;
import com.rental.vehicles.DTO.employee.EmployeeDTOResponse;


import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeAdmindTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    @DisplayName("Test Create Employee Admin")
    @Order(1)
    void testCreateEmployee_Admin() throws Exception {
        EmployeeDTORegister dto = new EmployeeDTORegister();
        dto.setName("John");
        dto.setSurname("Smith");
        dto.setEmail("Jhon.Smith2@example.com");
        dto.setPhone(123456789);
        dto.setAddress("123 Main St");


        ResponseEntity<?> response = employeeService.createEmployeeAdmin(dto);
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isInstanceOf(EmployeeDTOResponse.class);


    }

    @Test
    @DisplayName("Created Employee by Employee Admin")
    @Order(2)
    void created_newEmployee_by_employee_admin(){

        EmployeeDTORegister employeeDTORegister = new EmployeeDTORegister();
        employeeDTORegister.setName("Olivia");
        employeeDTORegister.setSurname("Gonzalez");
        employeeDTORegister.setEmail("olivia.gonzalez@example.es");
        employeeDTORegister.setPhone(123456789);
        employeeDTORegister.setAddress("La charca, 32");
        employeeDTORegister.setRole(Role_Employee.EMPLOYEE);

        ResponseEntity<?> response = employeeService.createEmployeeAdmin(employeeDTORegister);
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isInstanceOf(EmployeeDTOResponse.class);



    }
}
