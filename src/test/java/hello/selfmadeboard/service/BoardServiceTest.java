package hello.selfmadeboard.service;

import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    void 저장() {
        //given
        Board board = Board.builder()
                .title("hello")
                .content("hi")
                .writer("Lee")
                .build();

        //when
        Long savedId = boardService.save(board);

        //Then
        Assertions.assertThat(board).isEqualTo(boardService.findBoard(savedId));
    }

    @Test
    @Transactional
    void 수정(){
        //given
        Board board = Board.builder()
                .title("hello")
                .content("hi")
                .writer("Lee")
                .build();

        //when
        Long id = boardService.save(board);
        boardService.updateBoard(id,"Oh","kim","Hey");
        Board updateBoard = boardService.findBoard(id);

        //then
        Assertions.assertThat(updateBoard.getTitle()).isEqualTo("Oh");
        Assertions.assertThat(updateBoard.getWriter()).isEqualTo("kim");
        Assertions.assertThat(updateBoard.getContent()).isEqualTo("Hey");

    }


    @Test
    @Transactional
    void 삭제() {
        //given
        Board board = Board.builder()
                .title("hello")
                .content("hi")
                .writer("Lee")
                .build();

        //when
        Long savedId = boardService.save(board);
        boardService.deleteById(savedId);


        //Then
        org.junit.jupiter.api.Assertions.assertNull(boardService.findBoard(savedId));

    }

    @Test
    @Transactional
    void 조회수_증가() {

        //given
        Board board = Board.builder()
                .title("hello")
                .content("hi")
                .writer("Lee")
                .build();

        //when
        Long savedId = boardService.save(board);
        boardService.updateView(savedId);

        //Then
        Assertions.assertThat(boardService.findBoard(savedId).getView()).isEqualTo(1);
    }

    @Test
    @Transactional
    void 검색(){

        //given
        Board board = Board.builder()
                .title("Hello")
                .writer("lee")
                .content("hi")
                .build();
        Board board2 = Board.builder()
                .title("GoodBye")
                .writer("kim")
                .content("how are you")
                .build();

        String searchTitle = "Hello";

        //when
        Long saveId = boardService.save(board);
        Long saveId2 = boardService.save(board2);
        List<Board> boards = boardService.searchByTitle(searchTitle);

        //then
        Assertions.assertThat(boards).contains(board);
        Assertions.assertThat(boards).doesNotContain(board2);

    }
}