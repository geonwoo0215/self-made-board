package hello.selfmadeboard.repository;

import hello.selfmadeboard.domain.Member;
import hello.selfmadeboard.domain.Role;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    void 저장_조회() {
        //given
        Member member = Member.builder().email("geonwoo").nickName("Lee").passWord("123").role(Role.USER).build();

        //when
        Long savedMemberId = memberRepository.save(member);

        //then
        Member saveMember = memberRepository.findById(savedMemberId);


        Assertions.assertThat(member).isEqualTo(saveMember);
    }

    @Test
    void deleteById() {
    }
}