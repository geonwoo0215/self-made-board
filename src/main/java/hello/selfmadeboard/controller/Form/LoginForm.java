package hello.selfmadeboard.controller.Form;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginForm {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    @Builder
    public LoginForm(String loginId, String password){
        this.loginId = loginId;
        this.password = password;
    }
}
