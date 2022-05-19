package hello.selfmadeboard.repository;

import hello.selfmadeboard.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BoardRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Board board) {
        em.persist(board);
        return board.getId();
    }

    public Board findById(Long id) {
        return em.find(Board.class, id);
    }

}
