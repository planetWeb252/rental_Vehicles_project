package com.rental.vehicles.customer;

import com.rental.vehicles.DTO.customer.login.CustomerDtoLogin;
import com.rental.vehicles.DTO.customer.register.CustomerDTORegister;
import com.rental.vehicles.DTO.customer.register.CustomerDTOResponseRegister;
import com.rental.vehicles.DTO.employee.EmployeeDTOResponse;
import com.rental.vehicles.enums.ROLE_Customer;
import com.rental.vehicles.repositories.CustomerRepository;
import com.rental.vehicles.repositories.UserRepository;
import com.rental.vehicles.services.User.CustomerService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreatedCurstomerTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Created a new Customer")
    @Order(1)
    void createNewCustomer() {
        /*
        * Create a customer and the role when customer is being created, the role
        * must be ROLE_REGISTER
        * if test no passed, change the email (constraint unique)
        * */
        CustomerDTORegister customerDTORegister=new CustomerDTORegister();
        customerDTORegister.setName("Anastasio");
        customerDTORegister.setSurname("Ponce");
        customerDTORegister.setEmail("anastasio.ponce4@example.es");
        customerDTORegister.setPhone(123456789);
        customerDTORegister.setAddress("La calle el olivo 45");
        customerDTORegister.setPassword("1234");

        customerDTORegister.setRoleCustomer(ROLE_Customer.REGISTER);
        ResponseEntity<?> response = customerService.createCustomer(customerDTORegister);
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isInstanceOf(CustomerDTOResponseRegister.class);





    }

    @Test
    @DisplayName("Login customer in te app")
    @Order(2)
    void loginCustomer() {
        CustomerDtoLogin customerDtoLogin=new CustomerDtoLogin();
        customerDtoLogin.setName("Anastasio");
        customerDtoLogin.setPassword("1234");
        customerDtoLogin.setEmail("anastasio.ponce4@example.es");

        ResponseEntity<?> response = customerService.loginCustomer(customerDtoLogin);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isInstanceOf(String.class);


    }
}
