package hello.selfmadeboard.controller.form;


import hello.selfmadeboard.domain.Board;
import lombok.*;

@ToString
@Getter
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
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }


}
