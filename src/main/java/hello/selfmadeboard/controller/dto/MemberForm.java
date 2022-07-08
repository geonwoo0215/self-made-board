package hello.selfmadeboard.controller.dto;

import hello.selfmadeboard.domain.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberForm {

    @NotBlank
    private String loginId;
    @NotBlank
    private String name;
    @NotBlank
    private String password;

    public Member toEntity(){
        Member member = Member.builder()
                .loginId(loginId)
                .name(name)
                .password(password)
                .build();
        return member;
    }

    @Builder
    public MemberForm(String loginId, String name, String password){
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }

}

