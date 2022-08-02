package hello.selfmadeboard.controllerAdvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Map<String, String> badRequestHandle(MethodArgumentNotValidException e) {
        log.error("exception!", e);
        Map<String, String> errors =new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(a->errors.put(((FieldError)a).getField(),a.getDefaultMessage()));
        return errors;
    }

}
