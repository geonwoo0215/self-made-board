package hello.selfmadeboard.repository;

import hello.selfmadeboard.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @Transactional
    void 글_저장() {
        Board board = Board.builder().title("hello").writer("Lee").content("hi~").build();

        Long id = boardRepository.save(board);

        Board saveBoard = boardRepository.findById(id);

        Assertions.assertThat(saveBoard.getId()).isEqualTo(board.getId());

        Assertions.assertThat(saveBoard).isEqualTo(board);

    }
}