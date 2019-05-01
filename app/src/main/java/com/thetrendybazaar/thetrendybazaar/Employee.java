package com.thetrendybazaar.thetrendybazaar;

import java.sql.Date;

public class Employee {
    Integer employeeId;
    String position;
    Date dateJoined;
    Integer supervisorId;
    String firstName;
    String lastName;

    public Employee(Integer employeeId, String position, Date dateJoined, Integer supervisorId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.position = position;
        this.dateJoined = dateJoined;
        this.supervisorId = supervisorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", position='" + position + '\'' +
                ", dateJoined=" + dateJoined +
                ", supervisorId=" + supervisorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
