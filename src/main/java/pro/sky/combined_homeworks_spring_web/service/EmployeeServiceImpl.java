package pro.sky.combined_homeworks_spring_web.service;

import org.springframework.stereotype.Service;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeAlreadyAddedException;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeNotFoundException;
import pro.sky.combined_homeworks_spring_web.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public String getEmployeesInDepartment(int departmentId) {
        List<Employee> employeesList = new ArrayList<>(employees.values());
        String result = "";
        result += "Сотрудники отдела " + departmentId + " :<br>";

//        for (int i = 0; i < employeesList.size(); i++) {
//            if (employeesList.get(i).getDepartment() == departmentId) {
//                result += employeesList.get(i).getFullName() + " <br>";;
//            }
//        }
        result += employeesList.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .map(e -> e.getFullName() + "<br>")
                .collect(Collectors.joining());
        result += "<br>";
        return result;
    }

    @Override
    public String getListOfEmployeesByDepartment() {
        String result = "Список имён сотрудников по отделам <br>";
        for (int i = 1; i <= Employee.getNumberOfDepartments(); i++) {
            result += getEmployeesInDepartment(i);
        }
        return result;
    }

    public Employee findHighestPaidEmployeeInDepartment(int departmentId) {
        checkAvailabilityDepartment(departmentId);
        checkAvailabilityEmployees(departmentId);

        double highestSalaryInDepartment = 0;
        Employee highestPaidEmloyeeInDepartment = null;

        for (Employee currentEmployee : listOfEmployees) {
            if (currentEmployee != null && currentEmployee.getSalary() > highestSalaryInDepartment && currentEmployee.getDepartment() == departmentId) {
                highestSalaryInDepartment = currentEmployee.getSalary();
                highestPaidEmloyeeInDepartment = currentEmployee;
            }
        }
        return highestPaidEmloyeeInDepartment;
    }

    public void checkAvailabilityEmployees(int departmentId) {
        List<Employee> employeesList = new ArrayList<>(employees.values());
        if (employeesList.stream().anyMatch(e -> e.getDepartment() == departmentId)) {
            throw new EmployeeNotFoundException("В данном отделе нет сотрудников");
        }
    }

    public void checkAvailabilityDepartment(int departmentId) {
        if (departmentId <= 0 || departmentId > Employee.getNumberOfDepartments()) {
            throw new IllegalArgumentException("Неверный номер отдела, допустимое значение от 1 до "
                    + Employee.getNumberOfDepartments());
        }
    }
//
//    public Employee findLowestPaidEmployeeInDepartment(int departmentNumber) {
//        if (departmentNumber <= 0 || departmentNumber > 5) {
//            throw new IllegalArgumentException("Неверный номер отдела, допустимое значение от 1 до 5!");
//        }
//        double lowestSalaryInDepartment = 0;
//        Employee lowestPaidEmployeeInDepartment = null;
//        for (Employee currentEmployee : listOfEmployees) {
//            if (currentEmployee != null && currentEmployee.getDepartment() == departmentNumber) {
//                lowestSalaryInDepartment = currentEmployee.getSalary();
//            }
//        }
//        for (Employee currentEmployee : listOfEmployees) {
//            if (currentEmployee != null && currentEmployee.getDepartment() == departmentNumber && currentEmployee.getSalary() <= lowestSalaryInDepartment) {
//                lowestSalaryInDepartment = currentEmployee.getSalary();
//                lowestPaidEmployeeInDepartment = currentEmployee;
//            }
//        }
//        return lowestPaidEmployeeInDepartment;
//    }
//
//    public void changeEmployeeDepartment(String name, int newDepartmentNumber) {
//        int counter = 0;
//        while (counter < listOfEmployees.length) {
//            if (listOfEmployees[counter] != null && listOfEmployees[counter].getName().equalsIgnoreCase(name)) {
//                listOfEmployees[counter].setDepartment(newDepartmentNumber);
//                break;
//            }
//            counter++;
//        }
//        if (counter == listOfEmployees.length) {
//            throw new RuntimeException("Сотрудник не найден, проверьте правильность ввода данных!");
//        }
//    }
}
