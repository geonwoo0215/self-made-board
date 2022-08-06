package hello.selfmadeboard.service;

import hello.selfmadeboard.controller.form.BoardEditForm;
import hello.selfmadeboard.controller.form.BoardRequestForm;
import hello.selfmadeboard.controller.form.BoardResponseForm;
import hello.selfmadeboard.controller.form.BoardSearchForm;
import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Board> boards = IntStream.range(0, 20)
                .mapToObj(a-> Board.builder()
                        .title("제목"+a)
                        .content("내용"+a)
                        .build())
                .collect(Collectors.toList());

        boardRepository.saveAll(boards);

        BoardSearchForm boardSearchForm = BoardSearchForm.builder()
                .page(1)
                .size(10)
                .build();

        List<BoardResponseForm> boardResponseForms = boardService.list(boardSearchForm);

        Assertions.assertThat(boardResponseForms.size()).isEqualTo(10L);
        Assertions.assertThat("제목19").isEqualTo(boardResponseForms.get(0).getTitle());

    }

    @Test
    @DisplayName("글 제목 변경")
    void test4() {

        //given
        Board board = Board.builder()
                .title("title")
                .content("content")
                .build();

        boardRepository.save(board);

        BoardEditForm boardEditForm = BoardEditForm.builder()
                .title("changeTitle")
                .content("content")
                .build();

        boardService.edit(board.getId(), boardEditForm);

        boardRepository.findById(board.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + board.getId()));

        Assertions.assertThat("changeTitle").isEqualTo(boardEditForm.getTitle());
    }

    @Test
    @DisplayName("글 내용 변경")
    void test5() {

        //given
        Board board = Board.builder()
                .title("title")
                .content("content")
                .build();

        boardRepository.save(board);

        BoardEditForm boardEditForm = BoardEditForm.builder()
                .title("title")
                .content("changeContent")
                .build();

        boardService.edit(board.getId(), boardEditForm);

        boardRepository.findById(board.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + board.getId()));

        Assertions.assertThat("changeContent").isEqualTo(boardEditForm.getContent());
    }

    @Test
    @DisplayName("글 삭제")
    void test6() {

        //given
        Board board = Board.builder()
                .title("title")
                .content("content")
                .build();
        boardRepository.save(board);

        //when
        boardService.delete(board.getId());

        //then
        Assertions.assertThat(0).isEqualTo(boardRepository.count());


    }


}