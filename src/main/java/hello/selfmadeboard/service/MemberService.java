package hello.selfmadeboard.service;

import hello.selfmadeboard.domain.Member;
import hello.selfmadeboard.dto.MemberDto;
import hello.selfmadeboard.repository.MemberRepository;
import hello.selfmadeboard.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Transactional
    public Long join(MemberDto memberdto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberdto.setPassWord(passwordEncoder.encode(memberdto.getPassWord()));
        Long id = memberRepository.save(memberdto.toEntity());
        return id;
    }

    @Transactional
    public MemberDto findById(Long id) {
        Member member =  memberRepository.findById(id);

        MemberDto memberDto = MemberDto.builder()
                .email(member.getEmail())
                .nickName(member.getNickName())
                .passWord(member.getPassWord())
                .role(member.getRole())
                .build();
        return memberDto;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByName(username);

        return new CustomUserDetails(member);
    }
}
