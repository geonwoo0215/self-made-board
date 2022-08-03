package hello.selfmadeboard.controller.form;


import hello.selfmadeboard.domain.Board;
import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestForm {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @Builder
    public BoardRequestForm(String title, String content) {
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
