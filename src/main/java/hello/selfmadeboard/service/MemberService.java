package hello.selfmadeboard.service;

import hello.selfmadeboard.domain.Member;
import hello.selfmadeboard.dto.MemberDto;
import hello.selfmadeboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(MemberDto memberdto) {
        memberRepository.save(memberdto.toEntity());
        return memberdto.getId();
    }

    public MemberDto findById(Long id) {
        Member member =  memberRepository.findById(id);

        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickName(member.getNickName())
                .passWord(member.getPassWord())
                .role(member.getRole())
                .build();
        return memberDto;
    }


}
