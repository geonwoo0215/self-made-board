package hello.selfmadeboard.dto;

import hello.selfmadeboard.domain.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private int view;

    public Board toEntity(){
        Board build = Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Board board) {
        id = board.getId();
        title = board.getTitle();
        writer = board.getWriter();
        content = board.getContent();
        view = board.getView();
    }
}
