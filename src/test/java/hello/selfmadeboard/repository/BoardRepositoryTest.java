package hello.selfmadeboard.repository;

import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.Optional;

//@ExtendWith(SpringExtension.class)
//@DataJpaTest
@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    EntityManager em;

    BoardRepository boardRepository = new BoardRepository(em);

    BoardService boardService = new BoardService(boardRepository);
//
//    @AfterEach
//    private void after(){
//        em.clear();
//    }

    @Test
    @Transactional
    void 글_저장() {
        Board board = Board.builder().id(1L).title("hello").writer("Lee").content("hi~").build();

        Long id = boardService.save(board);

        Assertions.assertThat(board).isEqualTo(boardRepository.findById(id));
//        Board savedBoard = boardRepository.findById(id);
//
//        Board findBoard = boardRepository.findById(savedBoard.getId());

//        Board saveBoard = boardRepository.save(board);
//
//        Assertions.assertThat(saveBoard.getId()).isEqualTo(board.getId());

//        Assertions.assertThat(saveBoard).isEqualTo(board);
//        Assertions.assertThat(saveBoard).isSameAs(board);

//        Assertions.assertThat(findBoard.getId()).isEqualTo(board.getId());
//
//
//        Assertions.assertThat(findBoard.getTitle()).isEqualTo(board.getTitle());
//
//        Assertions.assertThat(findBoard.getContent()).isEqualTo(board.getContent());
//
//        Assertions.assertThat(findBoard.getWriter()).isEqualTo(board.getWriter());
    }
}