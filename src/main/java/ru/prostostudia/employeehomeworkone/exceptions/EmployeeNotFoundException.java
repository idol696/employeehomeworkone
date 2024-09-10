package ru.prostostudia.employeehomeworkone.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("Сотрудник не найден");
    }
}
