package com.rental.vehicles.repositories;

import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAll();
    Optional<Vehicle> findByLicensePlate(String licensePlate);
}
