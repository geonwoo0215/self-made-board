package hello.selfmadeboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.selfmadeboard.controller.form.BoardRequestForm;
import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https",uriHost = "api.selfmadeboard.com",uriPort = 443)
@ExtendWith(RestDocumentationExtension.class)
public class BoardControllerDocTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ObjectMapper objectMapper;

//    @BeforeEach
//    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(documentationConfiguration(restDocumentation))
//                .build();
//    }

    @Test
    @DisplayName("글 단건 조회")
    void test1() throws Exception {

        //given
        Board board = Board.builder()
                .title("title")
                .content("content")
                .build();
        boardRepository.save(board);

        String json = objectMapper.writeValueAsString(board);

        // expected
        mockMvc.perform(RestDocumentationRequestBuilders.get("/board/{boardId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("board-inquiry", RequestDocumentation.pathParameters(
                        RequestDocumentation.parameterWithName("boardId").description("글 Id")
                ), PayloadDocumentation.responseFields(
                        PayloadDocumentation.fieldWithPath("id").description("게시글 ID"),
                        PayloadDocumentation.fieldWithPath("title").description("제목"),
                        PayloadDocumentation.fieldWithPath("content").description("내용")

                )));


    }

    @Test
    @DisplayName("글 등록")
    void test2() throws Exception {

        //given
        BoardRequestForm board = BoardRequestForm.builder()
                .title("title")
                .content("content")
                .build();

        String json = objectMapper.writeValueAsString(board);

        // expected
        mockMvc.perform(RestDocumentationRequestBuilders.post("/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("board-create",
                        PayloadDocumentation.requestFields(
                        PayloadDocumentation.fieldWithPath("title").description("제목").attributes(Attributes.key("constraint").value("제목입력")),
                        PayloadDocumentation.fieldWithPath("content").description("내용").optional()

                )));


    }

}
