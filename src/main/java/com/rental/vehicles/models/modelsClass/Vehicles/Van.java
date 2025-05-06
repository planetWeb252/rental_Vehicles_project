package com.rental.vehicles.models.modelsClass.Vehicles;

import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Van extends Vehicle {
    public Van() {}
}
