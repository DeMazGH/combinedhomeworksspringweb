package pro.sky.combined_homeworks_spring_web.Service;

import org.springframework.stereotype.Service;
import pro.sky.combined_homeworks_spring_web.Employee;
import pro.sky.combined_homeworks_spring_web.Exeption.EmployeeAlreadyAddedException;
import pro.sky.combined_homeworks_spring_web.Exeption.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final List<Employee> listOfEmployees = new ArrayList<>();

    public void addNewEmployee(Employee newEmployee) {
        for (Employee currentEmoloyee : listOfEmployees) {
            if (currentEmoloyee != null &&
                    currentEmoloyee.getFirstName().equalsIgnoreCase(newEmployee.getFirstName()) &&
                    currentEmoloyee.getLastName().equalsIgnoreCase(newEmployee.getLastName())) {
                throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
            }
        }
        int counter = 0;
        while (counter < listOfEmployees.size()) {
            if (listOfEmployees.get(counter) == null) {
                listOfEmployees.set(counter, newEmployee);
                break;
            }
            counter++;
        }
        if (counter == listOfEmployees.size()) {
            listOfEmployees.add(newEmployee);
        }

    }

    public void deleteEmployee(String firstName, String lastName) {
        int counter = 0;
        while (counter < listOfEmployees.size()) {
            if (listOfEmployees.get(counter) != null &&
                    listOfEmployees.get(counter).getFirstName().equalsIgnoreCase(firstName) &&
                    listOfEmployees.get(counter).getLastName().equalsIgnoreCase(lastName)) {
                listOfEmployees.set(counter, null);
                break;
            }
            counter++;
        }
        if (counter == listOfEmployees.size()) {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
        for (Employee currentEmployee : listOfEmployees) {
            if (currentEmployee != null && currentEmployee.getFirstName().equalsIgnoreCase(firstName) &&
                    currentEmployee.getLastName().equalsIgnoreCase(lastName)) {
                return currentEmployee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
    }


    public List<Employee> getListOfEmployees() {
        return listOfEmployees;
    }


    @Override
    public String toString() {
        return listOfEmployees.get(0).toString();
    }
}
