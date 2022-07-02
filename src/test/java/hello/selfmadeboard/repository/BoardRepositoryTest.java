package hello.selfmadeboard.repository;

import hello.selfmadeboard.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;



@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    @Transactional
    void 저장(){

        //given
        Board board = Board.builder()
                .title("Hello")
                .writer("lee")
                .content("hi")
                .build();

        //when
        Long saveId = boardRepository.save(board);

        //then
        Assertions.assertThat(board).isEqualTo(boardRepository.findById(saveId));

    }

    @Test
    @Transactional
    void 삭제(){

        //given
        Board board = Board.builder()
                .title("Hello")
                .writer("lee")
                .content("hi")
                .build();

        //when
        Long saveId = boardRepository.save(board);
        boardRepository.deleteById(saveId);

        //then
        org.junit.jupiter.api.Assertions.assertNull(boardRepository.findById(saveId));
    }
}