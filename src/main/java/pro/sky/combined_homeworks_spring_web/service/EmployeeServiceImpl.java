package pro.sky.combined_homeworks_spring_web.service;

import org.springframework.stereotype.Service;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeAlreadyAddedException;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeNotFoundException;
import pro.sky.combined_homeworks_spring_web.model.Employee;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addNewEmployee(String firstName, String lastName, double salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        String desiredEmployee = firstName + " " + lastName;
        if (employees.containsKey(desiredEmployee)) {
            return employees.remove(desiredEmployee);
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String desiredEmployee = firstName + " " + lastName;
        if (employees.containsKey(desiredEmployee)) {
            return employees.get(desiredEmployee);
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }
    }

    @Override
    public Map<String, Employee> getEmployees() {

        return Collections.unmodifiableMap(employees);
    }
}
