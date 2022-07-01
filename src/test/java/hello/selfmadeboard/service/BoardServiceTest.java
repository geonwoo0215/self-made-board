package hello.selfmadeboard.service;

import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Test
    @Transactional
    void save() {
        //given
        Board board = Board.builder()
                .title("hello")
                .content("hi")
                .writer("Lee")
                .view(1)
                .build();

        //when
        Long savedId = boardService.save(board);

        //Then
        Assertions.assertThat(board).isEqualTo(boardRepository.findById(savedId));
    }

    @Test
    @Transactional
    void delete() {
        //given
        Board board = Board.builder()
                .title("hello")
                .content("hi")
                .writer("Lee")
                .view(1)
                .build();

        //when
        Long savedId = boardService.save(board);
        boardService.deleteById(savedId);
        Optional<Board> deleteUser = Optional.ofNullable(boardService.findBoard(savedId));

        //Then
        org.junit.jupiter.api.Assertions.assertFalse(deleteUser.isPresent());

    }
}