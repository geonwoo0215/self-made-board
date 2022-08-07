package hello.selfmadeboard.controller.form;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ErrorResponseForm {

    private String code;
    private String message;
    private final Map<String, String> validation = new HashMap<>();

    @Builder
    public ErrorResponseForm(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
}
