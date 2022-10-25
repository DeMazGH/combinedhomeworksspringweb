package pro.sky.combined_homeworks_spring_web.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeAlreadyAddedException;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeNotFoundException;
import pro.sky.combined_homeworks_spring_web.exeption.InvalidNameCharactersException;
import pro.sky.combined_homeworks_spring_web.model.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.combined_homeworks_spring_web.constants.EmployeeAndDepartmentServiceTestConstant.*;

class EmployeeServiceImplTest {

    private final EmployeeService out = new EmployeeServiceImpl();

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
    public void shouldReturnMapWithCorrectEmployees() {
        out.addNewEmployee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        out.addNewEmployee(FIRST_NAME_PETR, LAST_NAME_PETROV, SALARY_20000d, DEPARTMENT_2);
        Map<String, Employee> actual = out.getEmployees();

        Employee employee = new Employee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        Employee employee2 = new Employee(FIRST_NAME_PETR, LAST_NAME_PETROV, SALARY_20000d, DEPARTMENT_2);
        Map<String, Employee> expected = new HashMap<>();
        expected.put(FIRST_NAME_IVAN + " " + LAST_NAME_IVANOV, employee);
        expected.put(FIRST_NAME_PETR + " " + LAST_NAME_PETROV, employee2);

        assertEquals(actual, expected); // assertIterableEquals - почему он здесь не работает?
    }

    @Test
    public void shouldDoNothingInMethodValidateFirstAndLastName() {
        out.validateFirstAndLastName(FIRST_NAME_IVAN, LAST_NAME_IVANOV);
    }

    @ParameterizedTest
    @MethodSource("validateFirstAndLastNameParamTest")
    public void shouldReturnInvalidNameCharactersException(String firstName, String lastName) {
        assertThrows(InvalidNameCharactersException.class, () -> out.validateFirstAndLastName(firstName, lastName));
    }

    public static Stream<Arguments> validateFirstAndLastNameParamTest() {
        return Stream.of(
                Arguments.of(ILLEGAL_CHARACTERS_NAME, LAST_NAME_IVANOV),
                Arguments.of(FIRST_NAME_IVAN, ILLEGAL_CHARACTERS_NAME),
                Arguments.of(EMPTY_NAME, LAST_NAME_IVANOV),
                Arguments.of(FIRST_NAME_IVAN, EMPTY_NAME),
                Arguments.of(ONLY_SPACE_NAME, LAST_NAME_IVANOV),
                Arguments.of(FIRST_NAME_IVAN, ONLY_SPACE_NAME),
                Arguments.of(NULL_NAME, LAST_NAME_IVANOV),
                Arguments.of(FIRST_NAME_IVAN, NULL_NAME)
        );
    }

    @ParameterizedTest
    @MethodSource("checkAvailabilityDepartmentParamTest")
    public void shouldThrowsIllegalArgumentExceptionInMethodCheckAvailabilityDepartment(int departmentNumber) {
        assertThrows(IllegalArgumentException.class, () -> out.checkAvailabilityDepartment(departmentNumber));
    }

    public static Stream<Arguments> checkAvailabilityDepartmentParamTest() {
        return Stream.of(
                Arguments.of(DEPARTMENT_0),
                Arguments.of(DEPARTMENT_NEGATIVE_3),
                Arguments.of(DEPARTMENT_6)
        );
    }

    @Test
    public void shouldDoNothingInMethodCheckAvailabilityDepartment() {
        out.checkAvailabilityDepartment(DEPARTMENT_1);
        out.checkAvailabilityDepartment(DEPARTMENT_5);
    }
}