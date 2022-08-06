package hello.selfmadeboard.controller.form;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class BoardEditForm {

    @NotBlank(message = "타이틀을 입력하세요.")
    private final String title;

    @NotBlank(message = "콘텐츠를 입력해주세요.")
    private final String content;

    @Builder
    public BoardEditForm(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
