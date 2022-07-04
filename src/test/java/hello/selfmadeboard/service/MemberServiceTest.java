package hello.selfmadeboard.service;

import hello.selfmadeboard.domain.Member;
import hello.selfmadeboard.domain.Role;
import hello.selfmadeboard.dto.MemberDto;
import hello.selfmadeboard.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    MemberService memberService;
//
//    @Test
//    @Transactional
//    void 회원_가입(){
//        //given
//
//        MemberDto memberDto = MemberDto.builder()
//                .email("gw")
//                .username("Lee")
//                .passWord("123")
//                .role(Role.USER)
//                .build();
//
//        //when
//        Long memberId = memberService.join(memberDto);
//
//        //then
//        Assertions.assertThat(memberDto).isEqualTo(memberService.findById(memberId));
//    }

}