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

    private final Map<String, Employee> eployees;

    public EmployeeServiceImpl() {
        this.eployees = new HashMap<>();
    }

    @Override
    public Employee addNewEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (eployees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
        }
        eployees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (eployees.containsKey(employee.getFullName())) {
            return eployees.remove(employee.getFullName());
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (eployees.containsKey(employee.getFullName())) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }
    }

    @Override
    public Map<String, Employee> getEployees() {

        return Collections.unmodifiableMap(eployees);
    }
}
