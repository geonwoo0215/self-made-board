package hello.selfmadeboard.domain;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false,unique = true)
    private String loginId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Builder
    public Member(String loginId, String name, String password){
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}

