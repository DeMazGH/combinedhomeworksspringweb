package pro.sky.combined_homeworks_spring_web.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidNameCharachtersExeption extends IllegalArgumentException{
    public InvalidNameCharachtersExeption(String message) {
        super(message);
    }
}
