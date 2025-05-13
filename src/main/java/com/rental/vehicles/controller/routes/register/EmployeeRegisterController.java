package com.rental.vehicles.controller.routes.register;

import com.rental.vehicles.DTO.employee.EmployeeDTORegister;
import com.rental.vehicles.models.modelsClass.User.Employee;
import com.rental.vehicles.services.User.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/employee/register")
public class EmployeeRegisterController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRegisterController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/allEmployee")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return employeeService.allEmployee();
    }
    @PostMapping("/admin")
    public ResponseEntity<?> register(@Valid @RequestBody EmployeeDTORegister dto) {
        return employeeService.createEmployeeAdmin(dto);
    }
    @PostMapping("/employeeOthers")
    public ResponseEntity<?> registerEmployee(@Valid @RequestBody EmployeeDTORegister dto) {
        return employeeService.createEmployee(dto);
    }


}
