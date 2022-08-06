package hello.selfmadeboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.selfmadeboard.controller.form.BoardEditForm;
import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.controller.form.BoardRequestForm;
import hello.selfmadeboard.repository.BoardRepository;
import hello.selfmadeboard.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

//    @BeforeEach
//    void setUp() {
//        this.mockMvc = mockMvc = MockMvcBuilders.standaloneSetup(new BoardController(boardService))
//                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
//                .setViewResolvers(new ViewResolver() {
//                    @Override
//                    public View resolveViewName(String viewName, Locale locale) throws Exception {
//                        return new MappingJackson2JsonView();
//                    }
//                })
//                .build();
//    }






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

    @Test
    @DisplayName("전체 글 조회 요청")
    void test4() throws Exception {

        //given
        List<Board> boards = IntStream.range(0, 20)
                .mapToObj(a-> Board.builder()
                        .title("제목"+a)
                        .content("내용"+a)
                        .build())
                .collect(Collectors.toList());
        boardRepository.saveAll(boards);

        mockMvc.perform(MockMvcRequestBuilders.get("/board?page=0&size=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    @DisplayName("글 제목 수정")
    void test5() throws Exception {

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

        mockMvc.perform(MockMvcRequestBuilders.patch("/board/{boardId}",board.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boardEditForm)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    @DisplayName("글 삭제")
    void test6() throws Exception {

        //given
        Board board = Board.builder()
                .title("title")
                .content("content")
                .build();

        boardRepository.save(board);


        mockMvc.perform(MockMvcRequestBuilders.delete("/board/{boardId}", board.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

}