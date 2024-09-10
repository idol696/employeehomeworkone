package ru.prostostudia.employeehomeworkone;

public interface EmployeeServiceInterface {
    String printHeader();
    String printEmployeesTable();
    void demoFill();
    String deleteEmployee(String firstName, String lastName);
}
