package com.rental.vehicles.models.modelsClass;

import com.rental.vehicles.models.modelsAbstrac.Vehicle;
import com.rental.vehicles.models.modelsClass.User.Customer;
import com.rental.vehicles.models.modelsClass.User.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalPrice;
    private boolean aproved;
    private boolean returned;
    // relation with vehicle
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicles;
    // relation with customer
    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;
    // relation with employee
    @ManyToOne
    @JoinColumn(name = "aproved_by_id")
    private Employee aprovedBy;
}
