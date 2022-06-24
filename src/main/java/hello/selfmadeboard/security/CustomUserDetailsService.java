package hello.selfmadeboard.security;

import hello.selfmadeboard.domain.Member;
import hello.selfmadeboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByName(username);

//        MemberDto memberDto = MemberDto.builder()
//                .id(member.getId())
//                .email(member.getEmail())
//                .username(member.getUsername())
//                .passWord(member.getPassWord())
//                .role(member.getRole())
//                .build();

        return new CustomUserDetails(member);
    }
}
