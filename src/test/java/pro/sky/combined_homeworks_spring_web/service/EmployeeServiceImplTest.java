package pro.sky.combined_homeworks_spring_web.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.combined_homeworks_spring_web.constants.EmployeeAndDepartmentServiceTestConstant;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeAlreadyAddedException;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeNotFoundException;
import pro.sky.combined_homeworks_spring_web.model.Employee;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.combined_homeworks_spring_web.constants.EmployeeAndDepartmentServiceTestConstant.*;

class EmployeeServiceImplTest {

    private final EmployeeService out = new EmployeeServiceImpl();

    @Test
    void addNewEmployee() {
    }

    @Test
    public void shouldReturnEmployeeWithCorrectField() {
        Employee actual = out.addNewEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        Employee expected = new Employee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);

        assertEquals(actual, expected);
    }

    @Test
    public void mapShouldContainEmployeeWithCorrectField() {
        out.addNewEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        Employee expected = new Employee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        Employee actual = out.getEmployees().get(FIRST_NAME_IVAN + " " + LAST_NAME_IVANOV);
        assertEquals(actual, expected);
    }

    @Test
    public void shouldThrowsEmployeeAlreadyAddedException() {
        out.addNewEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> out.addNewEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1));
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    public void shouldReturnRemovedEmployee() {
        out.addNewEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        Employee expected = new Employee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        Employee actual = out.deleteEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV);

        assertEquals(actual, expected);
    }
    @Test
    public void mapShouldNotContainDesiredEmployeeAndReturnNull() {
        out.addNewEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        out.deleteEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV);
        Employee actual = out.getEmployees().get(FIRST_NAME_IVAN + " " + LAST_NAME_IVANOV);

        assertNull(actual);
    }

    @Test
    public void shouldThrowsEmployeeNotFoundExceptionInMethodDeleteEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> out.deleteEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV));
    }

    @Test
    void findEmployee() {
    }

    @Test
    public void shouldReturnDesiredEmployee() {
        out.addNewEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        Employee expected = new Employee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        Employee actual = out.findEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV);

        assertEquals(actual, expected);
    }

    @Test
    public void shouldThrowsEmployeeNotFoundExceptionInMethodFindEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV));
    }

    @Test
    void getEmployees() {
    }

    @Test
    public void shouldReturnMapWithCorrectEmployees() {
        out.addNewEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        out.addNewEmployee(FIRST_NAME_PETR, LAST_NAME_PETROV, SALARY_20000d, DEPARTMENT_2);

        Employee employee = new Employee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        Employee employee2 = new Employee(FIRST_NAME_PETR, LAST_NAME_PETROV, SALARY_20000d, DEPARTMENT_2);
        Map<String,Employee> expected = new HashMap<>();
        expected.put(FIRST_NAME_IVAN + " " + LAST_NAME_IVANOV, employee);
        expected.put(FIRST_NAME_PETR + " " + LAST_NAME_PETROV, employee2);

        Map<String,Employee> actual = out.getEmployees();

        assertEquals(actual, expected); // assertIterableEquals - почему не он не работает?
    }

    @Test
    void validateFirstAndLastName() {
    }

    @Test
    void checkAvailabilityDepartment() {
    }
}