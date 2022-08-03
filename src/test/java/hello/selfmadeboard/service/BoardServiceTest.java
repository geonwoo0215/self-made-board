package hello.selfmadeboard.service;

import hello.selfmadeboard.controller.form.BoardRequestForm;
import hello.selfmadeboard.controller.form.BoardResponseForm;
import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BoardServiceTest {


    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    private void clear() {
        boardRepository.deleteAll();
    }

    @Test
    @DisplayName("글 저장")
    void test1(){

        //given
        BoardRequestForm boardForm = BoardRequestForm.builder()
                .title("hello everyone")
                .content("my name is Lee")
                .build();

        //when
        boardService.write(boardForm);

        //then
        Assertions.assertThat(boardRepository.count()).isEqualTo(1L);
        Board board = boardRepository.findAll().get(0);
        Assertions.assertThat(board.getTitle()).isEqualTo("hello everyone");
        Assertions.assertThat(board.getContent()).isEqualTo("my name is Lee");

    }

    @Test
    @DisplayName("글 읽기")
    void test2() {

        //given
        BoardRequestForm boardForm = BoardRequestForm.builder()
                .title("hello everyone")
                .content("my name is Lee")
                .build();

        //when
        Long saveId = boardRepository.save(boardForm.toBoard()).getId();
        //then
        BoardResponseForm findBoard = boardService.read(saveId);
        Assertions.assertThat(findBoard.getTitle()).isEqualTo("hello everyone");
        Assertions.assertThat(findBoard.getContent()).isEqualTo("my name is Lee");

    }

    @Test
    @DisplayName("전체 글 조회")
    void test3() {

        //given
        Board board1 = Board.builder()
                .title("title1")
                .content("content1")
                .build();

        Board board2 = Board.builder()
                .title("title2")
                .content("content2")
                .build();

        Long saveId1 = boardRepository.save(board1).getId();
        Long saveId2 = boardRepository.save(board2).getId();

        List<BoardResponseForm> boards = boardService.list();

        Assertions.assertThat(boards.size()).isEqualTo(2L);

    }


}