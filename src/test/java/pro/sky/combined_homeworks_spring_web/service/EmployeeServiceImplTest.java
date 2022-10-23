package pro.sky.combined_homeworks_spring_web.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.combined_homeworks_spring_web.constants.EmployeeAndDepartmentServiceTestConstant;
import pro.sky.combined_homeworks_spring_web.model.Employee;

import java.util.Collections;

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
    void deleteEmployee() {
    }

    @Test
    void findEmployee() {
    }

    @Test
    void getEmployees() {
    }

    @Test
    void validateFirstAndLastName() {
    }

    @Test
    void checkAvailabilityDepartment() {
    }
}