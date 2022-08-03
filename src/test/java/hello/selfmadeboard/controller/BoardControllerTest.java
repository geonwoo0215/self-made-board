package hello.selfmadeboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.controller.form.BoardRequestForm;
import hello.selfmadeboard.repository.BoardRepository;
import hello.selfmadeboard.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@SpringBootTest
class BoardControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BoardController(boardService)).build();
    }

    @Test
    @DisplayName("저장 정상요청")
    void test1() throws Exception {

        BoardRequestForm boardForm = BoardRequestForm.builder()
                .title("hello everyone")
                .content("hi! my name is Lee")
                .build();

        String json = objectMapper.writeValueAsString(boardForm);

        mockMvc.perform(MockMvcRequestBuilders.post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Board board = boardRepository.findAll().get(0);

        Assertions.assertThat(board.getTitle()).isEqualTo("hello everyone");
        Assertions.assertThat(board.getContent()).isEqualTo("hi! my name is Lee");
    }

    @Test
    @DisplayName("저장 비정상 요청")
    void test2() throws Exception {
        BoardRequestForm boardForm = BoardRequestForm.builder()
                .content("hi! my name is Lee")
                .build();

        String json = objectMapper.writeValueAsString(boardForm);

        mockMvc.perform(MockMvcRequestBuilders.post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    @DisplayName("글 읽기 정상 요청")
    void test3() throws Exception {

        //given
        Board board = Board.builder()
                .title("title")
                .content("content")
                .build();

        Long saveId = boardRepository.save(board).getId();


        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/board/{boardId}",saveId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}