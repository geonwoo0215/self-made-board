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


}