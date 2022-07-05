package hello.selfmadeboard.domain;

import lombok.*;

import javax.persistence.*;

//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Member {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false,unique = true)
//    private String email;
//
//    @Column(nullable = false)
//    private String username;
//
//    @Column
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    @Builder
//    public Member(Long id, String email, String username, String password, Role role) {
//        this.id = id;
//        this.email = email;
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }
//}
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

