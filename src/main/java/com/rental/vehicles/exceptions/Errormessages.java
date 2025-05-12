package com.rental.vehicles.exceptions;

public class Errormessages {
    // Custoemr Messages

    public static final String INVALID_INPUT = "Invalid input";
    public static final String INVALID_CUSTOMER_PASSWORD= "The passwor is incorrect";
    public static final String CUSTOMER_NOT_FOUND=  "Customer not found";
    public static final String INVALID_CUSTOMER_LOGIN= "Incorrect login, the customer is login";



    // Employee Messages
    public static final String INVALID_ROLE_EMPLOYEE= "The Role is incorrect";
    public static final String INVALID_EMPLOYEE_NOT_ADMIN= "The employee is not admin";

    // Vehicle Messages
    public static final String VEHICLE_NOT_FOUND= "Vehicle not found";
    public static final String INVALID_VEHICLE= "The vehicle cannot be rental";
    public static final String INVALID_VEHICLE_RENTAL_DATE= "Invalid rental dates";

    //Rental Messages
    public static final String INVALID_RENTAL= "Dont exits rental by Status is Pending";
    public static final String INVALID_EMPLOYEE_NOT_FOUND = "Employee not found";
    public static final String IVALID_RENTAL_NOT_FOUND= "Rental not found";
    public static final String INVALID_RENTAL_NOT_APPROVED= "Rental not approved";


    public Errormessages() {
    }
}
