package com.rental.vehicles.repositories;

import com.rental.vehicles.enums.RentalStatus;
import com.rental.vehicles.models.modelsClass.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findRentalByStatus(RentalStatus status);

    Rental findRentalById(Long id);
}
