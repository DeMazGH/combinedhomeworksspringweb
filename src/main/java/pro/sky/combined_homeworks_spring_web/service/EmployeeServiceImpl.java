package pro.sky.combined_homeworks_spring_web.service;

import org.springframework.stereotype.Service;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeAlreadyAddedException;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeNotFoundException;
import pro.sky.combined_homeworks_spring_web.exeption.InvalidNameCharachtersExeption;
import pro.sky.combined_homeworks_spring_web.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addNewEmployee(String firstName, String lastName, double salary, int department) {
        checkAvailabilityDepartment(department);
        validateFirstAndLastName(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        validateFirstAndLastName(firstName, lastName);

        String desiredEmployee = firstName + " " + lastName;
        if (employees.containsKey(desiredEmployee)) {
            return employees.remove(desiredEmployee);
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        validateFirstAndLastName(firstName, lastName);

        String desiredEmployee = firstName + " " + lastName;
        if (employees.containsKey(desiredEmployee)) {
            return employees.get(desiredEmployee);
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных");
        }
    }

    @Override
    public Map<String, Employee> getEmployees() {
        return Collections.unmodifiableMap(employees);
    }

    @Override
    public String getEmployeesInDepartment(int departmentId) {
        List<Employee> employeesList = new ArrayList<>(employees.values());
        String result = "";
        result += "Сотрудники отдела " + departmentId + " :<br>";
        result += employeesList.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .map(e -> e.getFullName() + "<br>")
                .collect(Collectors.joining());
        result += "<br>";
        return result;
    }

    @Override
    public String getListOfEmployeesByDepartment() {
        Employee employee = new Employee("test", "test", 1, 1);
        StringBuilder result = new StringBuilder("Список имён сотрудников по отделам <br>");
        for (int i = 1; i <= employee.getNumberOfDepartments(); i++) {
            result.append(getEmployeesInDepartment(i));
        }
        return result.toString();
    }

    @Override
    public Employee findHighestPaidEmployeeInDepartment(int departmentId) {
        checkAvailabilityDepartment(departmentId);
        checkAvailabilityEmployees(departmentId);

        List<Employee> employeesList = new ArrayList<>(employees.values());
        double highestSalaryInDepartment = employeesList.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .map(Employee::getSalary)
                .max(Double::compareTo)
                .get();
        Employee highestPaidEmployeeInDepartment = employeesList.stream()
                .filter(e -> e.getSalary() == highestSalaryInDepartment).findFirst().get();
        return highestPaidEmployeeInDepartment;
    }

    @Override
    public Employee findLowestPaidEmployeeInDepartment(int departmentId) {
        checkAvailabilityDepartment(departmentId);
        checkAvailabilityEmployees(departmentId);

        List<Employee> employeesList = new ArrayList<>(employees.values());
        double lowestSalaryInDepartment = employeesList.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .map(Employee::getSalary)
                .min(Double::compareTo)
                .get();
        Employee lowestPaidEmployeeInDepartment = employeesList.stream()
                .filter(e -> e.getSalary() == lowestSalaryInDepartment).findFirst().get();
        return lowestPaidEmployeeInDepartment;
    }

    public void checkAvailabilityEmployees(int departmentId) {
        List<Employee> employeesList = new ArrayList<>(employees.values());
        if (employeesList.isEmpty()) {
            throw new EmployeeNotFoundException("Сотрудников не найдено");
        }
        if (employeesList.stream().allMatch(e -> e.getDepartment() != departmentId)) {
            throw new EmployeeNotFoundException("В данном отделе нет сотрудников");
        }
    }

    public void checkAvailabilityDepartment(int departmentId) {
        Employee employee = new Employee("test", "test", 1, 1);
        if (departmentId <= 0 || departmentId >= employee.getNumberOfDepartments()) {
            throw new IllegalArgumentException("Неверный номер отдела, допустимое значение от 1 до "
                    + employee.getNumberOfDepartments());
        }
    }

    void validateFirstAndLastName(String firstName, String lastName) {
        if (isAllBlank(firstName) || isAllBlank(lastName)
                || !isAlpha(firstName) || !isAlpha(lastName)) {
            throw new InvalidNameCharachtersExeption("Неверно указаны имя или фамилия");
        }
    }
}
