package ru.prostostudia.employeehomeworkone;

import ru.prostostudia.employeehomeworkone.exceptions.custom.EmployeeNameException;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;

    public Employee() {
        throw new EmployeeNameException("Задайте имя и фамилию!");
    }

    public Employee(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.isBlank())
            throw new EmployeeNameException("Нельзя чтобы основное имя было пустым");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank())
            throw new EmployeeNameException("Нельзя чтобы фамилия была пустой");
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return  firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
