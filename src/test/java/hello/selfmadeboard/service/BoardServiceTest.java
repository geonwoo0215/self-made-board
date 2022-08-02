package hello.selfmadeboard.service;

import hello.selfmadeboard.controller.form.BoardForm;
import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {


    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("글 저장")
    void test1(){

        //given
        BoardForm boardForm = BoardForm.builder()
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


}