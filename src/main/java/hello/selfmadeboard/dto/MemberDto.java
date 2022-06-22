package hello.selfmadeboard.dto;

import hello.selfmadeboard.domain.Member;
import hello.selfmadeboard.domain.Role;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MemberDto {

    private Long id;

    private String email;

    private String nickName;

    private String passWord;

    private Role role;

    public Member toEntity() {
        Member member = Member.builder()
                .email(email)
                .nickName(nickName)
                .passWord(passWord)
                .role(role.USER)
                .build();
        return member;
    }

    @Builder
    public MemberDto(String email, String nickName, String passWord, Role role){

        this.email = email;
        this.nickName = nickName;
        this.passWord = passWord;
        this.role = role;
    }

}
