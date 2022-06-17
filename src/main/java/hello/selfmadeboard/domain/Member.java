package hello.selfmadeboard.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String nickName;

    @Column
    private String passWord;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(Long id, String email, String nickName, String passWord, Role role) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.passWord = passWord;
        this.role = role;
    }
}
