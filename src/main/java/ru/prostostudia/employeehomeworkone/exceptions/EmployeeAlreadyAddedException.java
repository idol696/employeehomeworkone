package ru.prostostudia.employeehomeworkone.exceptions;

public class EmployeeAlreadyAddedException extends RuntimeException {
    public EmployeeAlreadyAddedException() {
        super("Сотрудник уже имеется");
    }
}
