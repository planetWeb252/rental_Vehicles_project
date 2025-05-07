package com.rental.vehicles.controller.routePublic.register;

import com.rental.vehicles.models.DTO.employee.EmployeeDTORegister;
import com.rental.vehicles.models.DTO.employee.EmployeeDTOResponse;
import com.rental.vehicles.services.User.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/employee/register")
public class EmployeeRegister {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeRegister(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTOResponse> register(@Valid @RequestBody EmployeeDTORegister dto) {
        return employeeService.createEmployee(dto);
    }




}
