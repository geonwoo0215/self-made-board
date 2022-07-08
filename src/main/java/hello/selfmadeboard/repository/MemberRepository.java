package hello.selfmadeboard.repository;


import hello.selfmadeboard.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepository {

    private final EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m").getResultList();
    }

    public Optional<Member> findByLoginId(String loginId){
        return em.createQuery("select m from Member m where m.loginId =  :loginId", Member.class).setParameter("loginId", loginId)
                .getResultStream().filter(m -> m.getLoginId().equals(loginId)).findFirst();
    }

}
