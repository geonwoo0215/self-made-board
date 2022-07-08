package hello.selfmadeboard.service;

import hello.selfmadeboard.domain.Member;
import hello.selfmadeboard.controller.Form.MemberForm;
import hello.selfmadeboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void save(MemberForm memberForm){
        memberRepository.save(memberForm.toEntity());
    }

    public Member findById(Long id){
        return memberRepository.findById(id);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member login(String loginId, String password){
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

}
