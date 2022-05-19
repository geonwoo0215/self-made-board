package hello.selfmadeboard.repository;

import hello.selfmadeboard.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    EntityManager em;

    @AfterEach
    private void after(){
        em.clear();
    }

    @Test
    void 글_저장() {
        Board board = Board.builder()
                .id(1L)
                .title("hello")
                .writer("Lee")
                .content("hi~")
                .build();
        Long id = boardRepository.save(board);

        Board savedBoard = boardRepository.findById(id);

        Board findBoard = boardRepository.findById(savedBoard.getId());

        Assertions.assertThat(savedBoard).isEqualTo(board);

        Assertions.assertThat(savedBoard.getId()).isEqualTo(board.getId());

        Assertions.assertThat(savedBoard.getTitle()).isEqualTo(board.getTitle());

        Assertions.assertThat(savedBoard.getContent()).isEqualTo(board.getContent());

        Assertions.assertThat(savedBoard.getWriter()).isEqualTo(board.getWriter());
    }
}