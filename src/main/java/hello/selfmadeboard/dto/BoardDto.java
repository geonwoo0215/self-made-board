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
                .view(view)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long id, String title, String writer, String content, int view) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.view = view;
    }
}
