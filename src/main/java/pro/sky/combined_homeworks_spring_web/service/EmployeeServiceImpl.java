package pro.sky.combined_homeworks_spring_web.service;

import org.springframework.stereotype.Service;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeNotFoundException;
import pro.sky.combined_homeworks_spring_web.model.Employee;

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
        String key = firstName + " " + lastName;
//        if (eployees.get()) {
//            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
//        }
        eployees.put(key, employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        String key = firstName + " " + lastName;
        if (eployees.containsKey(key)) {
            eployees.remove(key);
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        String key = firstName + " " + lastName;
        if (eployees.containsKey(key)) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }
    }

//    @Override
//    public List<Employee> getEployees() {
//        return Collections.unmodifiableList(eployees);
//    }
}
