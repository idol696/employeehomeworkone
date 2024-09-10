package ru.prostostudia.employeehomeworkone;

import org.springframework.stereotype.Service;
import ru.prostostudia.employeehomeworkone.exceptions.EmployeeAlreadyAddedException;
import ru.prostostudia.employeehomeworkone.exceptions.EmployeeNotFoundException;
import ru.prostostudia.employeehomeworkone.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    private static final String TABLE_HEADER = "<table border=1 style='border-collapse: border-collapse>'>";
    private static final String TABLE_TH = "<th>Имя</th><th>Фамилия</th><th>Действие</th>";
    private final List<Employee> employeesBook = new ArrayList<>();
    private int maxEmployees = Integer.MAX_VALUE;

    public EmployeeService() {
        demoFill();
    }
    public void demoFill() {
        employeesBook.clear();
        addEmployee("Илья", "Бабушкин");
        addEmployee("Игорь", "Мусинькин");
        addEmployee("Виталий", "Хазбулатов");
        addEmployee("Иван", "Познер");
        addEmployee("Исаакий", "Волондемортов");
        addEmployee("Ирина", "Дудина");
        addEmployee("Иннокентий", "Смактуновский");
        addEmployee("Наталья", "Бузинова");
        addEmployee("Навелий", "Навеяло");
        addEmployee("Прасковья", "Прошкина");
        setMaxEmployees(60);
    }

    public int getMaxEmployees() {
        return maxEmployees;
    }

    public void setMaxEmployees(int maxEmployees) {
        if (employeesBook.size() > maxEmployees) throw new EmployeeStorageIsFullException();
        this.maxEmployees = maxEmployees;
    }

    public void addEmployee(String firstName, String lastName) {
        if (employeesBook.size() >= maxEmployees) throw new EmployeeStorageIsFullException();
        boolean addFlag = false;
        try {
            getEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            employeesBook.add(new Employee(firstName, lastName));
            addFlag = true;
        }
        if (!addFlag) throw new EmployeeAlreadyAddedException();
    }

    public String deleteEmployee(String firstName, String lastName) {
        boolean deleteFlag = true;
        String message = "";
        try {
            getEmployee(firstName,lastName);
             message = "Сотрудник " + getEmployee(firstName,lastName) + " удален";
        } catch (EmployeeNotFoundException e) {
            message = e.getMessage();
            deleteFlag = false;
        }
        if (deleteFlag) employeesBook.remove(getEmployee(firstName,lastName));
        return message;
    }

    public Employee getEmployee(String firstName, String lastName) {
        for (Employee employee : employeesBook) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public String printHeader() {
    return printMenu() + printSearch();
    }

    public String printMenu() {
        String style = "  <style>\n" +
                "   ul.hr {\n" +
                "    margin: 0;\n" +
                "    padding: 4px;\n" +
                "   }\n" +
                "   ul.hr li {\n" +
                "    display: inline;\n" +
                "    margin-right: 5px;\n" +
                "    border: 1px solid #000;\n" +
                "    padding: 3px;\n" +
                "   }\n" +
                "  </style>";
        return style + "<ul class='hr'>" +
                "<li><a href='/add'>Добавить сотрудника</a></li>" +
                "<li><a href='/changeMax'>Изменить количество сотрудников</a></li>" +
                "<li><a href='/reInit'>Восстановить данные</a></li>" +
                "</ul>";
    }

    public String printSearch() {
        return "<h3>Найти сотрудника</h3>" +
                "<form action=\"search\" method=\"get\"><label>Имя</label><input name=\"firstName\" type=\"text\" " +
                "required=\"required\" id=\"firstName\"><label>Фамилия</label><input name=\"lastName\" type=\"text\" " +
                "id=\"lastName\"><input type=\"submit\" formaction=\"search\" formmethod=\"GET\" value=\"Найти\"></form>";
    }

    public String printSearchEmployee(Employee employee) {
        String result = "<h3>Сотрудник найден:" + "</h3>\r\n" + TABLE_HEADER +
                "<tr>" + TABLE_TH + "</tr>";
        return result + "<tr>" + printEmployeeRow(employee) + "</tr></table>";
    }

    public String printEmployeesTable() {
        String result = "<h3>Максимальное количество сотрудников:" + getMaxEmployees() + "</h3>\r\n" + TABLE_HEADER +
                "<tr><th>№пп</th>" + TABLE_TH + "</tr>";
        int count = 0;
        for (Employee employee : employeesBook) {
            result += String.format("<tr><td>%d</td>%s</tr>\r\n",++count,printEmployeeRow(employee));
        }
        return result + "</table>";
    }

    private String printEmployeeRow(Employee employee) {
        return String.format("<td>%1$s</td><td>%2$s</td><td><a href=\"/del?firstName=%1$s&lastName=%2$s\">Удалить</a></td>",
                employee.getFirstName(),employee.getLastName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeService that = (EmployeeService) o;
        return maxEmployees == that.maxEmployees && Objects.equals(employeesBook, that.employeesBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeesBook, maxEmployees);
    }
}
