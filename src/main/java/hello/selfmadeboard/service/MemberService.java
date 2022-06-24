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
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;


    @Transactional
    public Long join(MemberDto memberdto) {
        memberdto.setPassword(encoder.encode(memberdto.getPassword()));
        Long id = memberRepository.save(memberdto.toEntity());
        return id;
    }

    @Transactional
    public MemberDto findById(Long id) {
        Member member =  memberRepository.findById(id);

        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .username(member.getUsername())
                .password(member.getPassword())
                .role(member.getRole())
                .build();
        return memberDto;
    }



}
