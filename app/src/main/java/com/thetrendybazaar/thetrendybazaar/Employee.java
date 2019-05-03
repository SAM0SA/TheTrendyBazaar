package com.thetrendybazaar.thetrendybazaar;

import java.util.Date;

public class Employee {
    Integer employeeId;
    String position;
    Date dateJoined;
    Integer supervisorId;
    String firstName;
    String lastName;
    String password;
    static int currentEmployeeId = -1;

    public Employee(Integer employeeId, String position, Date dateJoined, Integer supervisorId, String firstName, String lastName, String password) {
        this.employeeId = employeeId;
        this.position = position;
        this.dateJoined = dateJoined;
        this.supervisorId = supervisorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
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
