package hello.selfmadeboard.controller.form;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseForm {

    private Long id;
    private String title;
    private String content;

    @Builder
    public BoardResponseForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
