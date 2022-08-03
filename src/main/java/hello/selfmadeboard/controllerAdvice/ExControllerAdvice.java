package hello.selfmadeboard.controllerAdvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> badRequestHandle(MethodArgumentNotValidException e) {
        log.error("잘못 된 입력입니다!", e);
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(a -> errors.put(((FieldError) a).getField(), a.getDefaultMessage()));
        return errors;
    }

}
