package ru.prostostudia.employeehomeworkone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeServiceInterface employeeService;


    public EmployeeController(EmployeeServiceInterface employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String printPage() {
        String page = employeeService.printHeader();
        page += employeeService.printEmployeesTable();
        return page;
    }

    @GetMapping(path = "/add")
    public String employeeAdd() {
        employeeService.demoFill();
        return printPage();
    }

    @GetMapping(path = "/del")
    public String employeeDelete(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName ) {
        String result = employeeService.deleteEmployee(firstName, lastName);
        return printPage() + result;
    }


}

