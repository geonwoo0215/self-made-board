package hello.selfmadeboard.controller;

import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.service.BoardService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardService boardService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new BoardController(boardService))
                .build();
    }

    @Test
    void 초기화면() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("boardList"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void 게시글_작성() throws  Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/boardForm")
                        .param("title","hi")
                        .param("writer","lee")
                        .param("content","hello"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    void 게시글_출력() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("boardList"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void 게시글_수정_폼() throws Exception {
        Board board = Board.builder()
                .title("hello")
                .writer("lee")
                .content("hi")
                .build();
        boardService.save(board);
        mockMvc.perform(MockMvcRequestBuilders.get("/boardContent/1/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void 게시글_수정() throws Exception{
        Board board = Board.builder()
                .title("hello")
                .writer("lee")
                .content("hi")
                .build();
        boardService.save(board);

        mockMvc.perform(MockMvcRequestBuilders.post("/boardContent/1/edit")
                        .param("title", "hello2")
                        .param("writer", "kim")
                        .param("content", "hi2"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

    }

    @Test
    void 게시글_삭제() throws Exception{
        Board board = Board.builder()
                .title("hello")
                .writer("lee")
                .content("hi")
                .build();
        boardService.save(board);

        mockMvc.perform(MockMvcRequestBuilders.delete("/boardContent/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    void 게시글_검색() throws Exception{
        Board board = Board.builder()
                .title("hello")
                .writer("lee")
                .content("hi")
                .build();
        boardService.save(board);

        mockMvc.perform(MockMvcRequestBuilders.get("/boardSearch")
                        .param("keyword","hello"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("boardList"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}