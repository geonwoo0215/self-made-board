package hello.selfmadeboard.dto;

import hello.selfmadeboard.domain.Board;
import lombok.Data;

@Data
public class BoardDto {

    private Long id;
    private String title;
    private String writer;
    private String content;

    public BoardDto(Board board) {
        id = board.getId();
        title = board.getTitle();
        writer = board.getWriter();
        content = board.getContent();
    }
}
