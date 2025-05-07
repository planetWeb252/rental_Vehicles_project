package com.rental.vehicles.services.User;

import com.rental.vehicles.models.DTO.employee.EmployeeDTORegister;
import com.rental.vehicles.models.DTO.employee.EmployeeDTOResponse;
import com.rental.vehicles.models.modelsClass.User.Employee;
import com.rental.vehicles.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<EmployeeDTOResponse> createEmployee(@Valid EmployeeDTORegister dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setSurname(dto.getSurname());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setAddress(dto.getAddress());
        employee.setRoleEmployee(dto.getRole());

        Employee saved=employeeRepository.save(employee);
        EmployeeDTOResponse employeeDTOResponse = new EmployeeDTOResponse();
        employeeDTOResponse.setName(saved.getName());
        employeeDTOResponse.setSurname(saved.getSurname());
        employeeDTOResponse.setEmail(saved.getEmail());
        return new ResponseEntity<>(employeeDTOResponse, HttpStatus.CREATED);
    }
}
