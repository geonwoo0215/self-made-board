package hello.selfmadeboard.repository;

import hello.selfmadeboard.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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