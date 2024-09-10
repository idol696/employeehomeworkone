package ru.prostostudia.employeehomeworkone.exceptions;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException() {
        super("Превышен лимит сотрудников в фирме");
    }
}
