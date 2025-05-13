package com.rental.vehicles.rental;
import com.rental.vehicles.DTO.rental.RentalDTO;
import com.rental.vehicles.DTO.rental.RentalDTOAproved;
import com.rental.vehicles.DTO.rental.RentalDTOResponse;
import com.rental.vehicles.repositories.RentalRepository;
import com.rental.vehicles.services.RentalService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RentalControllerTest {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private RentalService rentalService;

    @Test
    @DisplayName("Create Rental should return 200")
    @Order(1)
    void createRentalTest() throws Exception {

        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setEmail("juan.perez6@example.com");
        rentalDTO.setStartDate(LocalDate.of(2025, 5, 13));
        rentalDTO.setEndDate(LocalDate.of(2026, 10, 5));
        ResponseEntity<?> response = rentalService.createRental(rentalDTO, "CAR123XYZ");
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isInstanceOf(RentalDTOResponse.class);


    }
    @Test
    @DisplayName("Aprovedd rental should return String")
    @Order(2)
    void approveRentalTest() throws Exception {
        RentalDTOAproved rentalDTO = new RentalDTOAproved();
        rentalDTO.setEmployeeEmail("laura.garcia2@example.com");
        rentalDTO.setLicensePlate("CAR123XYZ");
        ResponseEntity<?> response = rentalService.approveRental(rentalDTO, 3L);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isInstanceOf(String.class);
    }
}
