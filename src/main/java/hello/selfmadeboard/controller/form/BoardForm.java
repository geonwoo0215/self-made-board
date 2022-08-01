package hello.selfmadeboard.controller.form;


import hello.selfmadeboard.domain.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardForm {

    private String title;

    private String content;

    @Builder
    public BoardForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Board toBoard() {
        Board board = Board.builder()
                .title(this.title)
                .content(this.content)
                .build();
        return board;
    }


}
