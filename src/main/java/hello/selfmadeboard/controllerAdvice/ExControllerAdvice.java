package hello.selfmadeboard.controllerAdvice;

import hello.selfmadeboard.controller.form.ErrorResponseForm;
import hello.selfmadeboard.exception.BoardException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseForm badRequestHandle(MethodArgumentNotValidException e) {
        log.error("잘못된 요청!");
        ErrorResponseForm response = ErrorResponseForm.builder()
                .code("400")
                .message("잘못된 요청합니다.")
                .build();

        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return response;
    }



    @ExceptionHandler(BoardException.class)
    public ResponseEntity<ErrorResponseForm> boardException(BoardException e) {
        log.error("찾을 수 없습니다!");

        int statusCode = e.getStatusCode();

        ErrorResponseForm response = ErrorResponseForm.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(statusCode).body(response);

    }
}
