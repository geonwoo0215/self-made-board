package hello.selfmadeboard.controller.dto;

import hello.selfmadeboard.domain.Board;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardForm {

    private Long id;

    @NotBlank
    private String title;
    private String writer;
    private String content;

    private Integer view;

    public Board toEntity(){
        Board board = Board.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return board;
    }

    @Builder
    public BoardForm(Long id, String title, String writer, String content, Integer view) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.view = view;
    }

}
