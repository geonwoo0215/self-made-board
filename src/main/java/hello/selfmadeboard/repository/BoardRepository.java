package hello.selfmadeboard.repository;

import hello.selfmadeboard.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public Long save(Board board) {
        em.persist(board);
        return board.getId();
    }

    public Board findById(Long id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll(){
        return em.createQuery("select m from Board m")
                .getResultList();
    }

}
