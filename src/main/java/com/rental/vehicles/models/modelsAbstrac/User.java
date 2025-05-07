package com.rental.vehicles.models.modelsAbstrac;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer phone;
    private String address;
    @Column(unique = true)
    private String email;


    public User(String name, String surname, int phone, String address, String email, String password) {
    }

}
