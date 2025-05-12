package com.rental.vehicles.repositories.VehicleRepositories;

import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAll();
}
