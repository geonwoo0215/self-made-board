package hello.selfmadeboard.controller.dto;

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

    private String username;

    private String password;

    private Role role;

    public Member toEntity() {
        Member member = Member.builder()
                .id(id)
                .email(email)
                .username(username)
                .password(password)
                .role(role.USER)
                .build();
        return member;
    }

    @Builder
    public MemberDto(Long id, String email, String username, String password, Role role){
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
