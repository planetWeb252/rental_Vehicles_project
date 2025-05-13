package com.rental.vehicles.services.User;

import com.rental.vehicles.DTO.employee.EmployeeDTORegister;
import com.rental.vehicles.DTO.employee.EmployeeDTOResponse;
import com.rental.vehicles.enums.Role_Employee;
import com.rental.vehicles.exceptions.Employee.EmployeeExceptions;
import com.rental.vehicles.exceptions.Errormessages;
import com.rental.vehicles.models.modelsClass.User.Employee;
import com.rental.vehicles.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<EmployeeDTOResponse> createEmployeeAdmin(@Valid EmployeeDTORegister dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setSurname(dto.getSurname());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setAddress(dto.getAddress());
        if (dto.getRole() == null) {
            employee.setRoleEmployee(Role_Employee.ROLE_ADMIN);
        }

        Employee saved = employeeRepository.save(employee);
        EmployeeDTOResponse employeeDTOResponse = new EmployeeDTOResponse();
        employeeDTOResponse.setName(saved.getName());
        employeeDTOResponse.setSurname(saved.getSurname());
        employeeDTOResponse.setEmail(saved.getEmail());
        return new ResponseEntity<>(employeeDTOResponse, HttpStatus.CREATED);
    }

    /*
     * in this function the employee admin can create other employees
     * */
    public ResponseEntity<?> createEmployee(@Valid EmployeeDTORegister dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setSurname(dto.getSurname());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setAddress(dto.getAddress());
        Role_Employee newEmployeeRol = dto.getRole();
        if (!newEmployeeRol.equals(Role_Employee.ROLE_EMPLOYEE) && !newEmployeeRol.equals(Role_Employee.ROLE_MECHANIC)
                && !newEmployeeRol.equals(Role_Employee.ROLE_ADMIN)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new EmployeeExceptions(Errormessages.INVALID_ROLE_EMPLOYEE));
        } else {

            employee.setRoleEmployee(dto.getRole());
        }
        //Employee Admin find by role
        Optional<Employee> employeeAdminOptional = employeeRepository.findRoleEmployeeByEmail(dto.getAdminEmail());
        if (employeeAdminOptional.isPresent()) {
            Role_Employee rol = employeeAdminOptional.get().getRoleEmployee();
            // if the rol not is Admin
            if (!rol.equals(Role_Employee.ROLE_ADMIN)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new EmployeeExceptions(Errormessages.INVALID_ROLE_EMPLOYEE_NOT_ADMIN));
            }
        }
        // save the new Employee
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTOResponse employeeDTOResponse = new EmployeeDTOResponse();
        employeeDTOResponse.setName(savedEmployee.getName());
        employeeDTOResponse.setSurname(savedEmployee.getSurname());
        employeeDTOResponse.setEmail(savedEmployee.getEmail());
        return new ResponseEntity<>(employeeDTOResponse, HttpStatus.CREATED);
    }


    public ResponseEntity<List<Employee>> allEmployee() {
        List<Employee>employees=employeeRepository.findAll();
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }
}
