package pro.sky.combined_homeworks_spring_web.Service;

import org.springframework.stereotype.Service;
import pro.sky.combined_homeworks_spring_web.Employee;
import pro.sky.combined_homeworks_spring_web.Exeption.EmployeeAlreadyAddedException;
import pro.sky.combined_homeworks_spring_web.Exeption.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> listOfEmployees;

    public EmployeeServiceImpl() {
        this.listOfEmployees = new ArrayList<>();
    }

    @Override
    public Employee addNewEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (listOfEmployees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
        }
        listOfEmployees.add(employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (listOfEmployees.contains(employee)) {
            listOfEmployees.remove(employee);
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (listOfEmployees.contains(employee)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }
    }

    @Override
    public List<Employee> getListOfEmployees() {
        return Collections.unmodifiableList(listOfEmployees);
    }
}
