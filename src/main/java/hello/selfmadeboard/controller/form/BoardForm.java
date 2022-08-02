package hello.selfmadeboard.controller.form;


import hello.selfmadeboard.domain.Board;
import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardForm {

    @NotBlank
    private String title;

    @NotBlank
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
