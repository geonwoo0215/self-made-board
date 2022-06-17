package hello.selfmadeboard.repository;


import hello.selfmadeboard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public void deleteById(Long id){
        em.createQuery("delete from Member m where m.id= :id").executeUpdate();
    }

}
