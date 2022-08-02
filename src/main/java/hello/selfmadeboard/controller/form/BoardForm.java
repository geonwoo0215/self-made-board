package hello.selfmadeboard.controller.form;


import hello.selfmadeboard.domain.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardForm {

    @NotBlank
    @Size(min = 8, max = 20)
    private String title;

    @NotBlank
    @Size(min = 10)
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
