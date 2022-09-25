package pro.sky.combined_homeworks_spring_web.Exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
