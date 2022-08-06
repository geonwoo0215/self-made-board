package hello.selfmadeboard.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class BoardEditor {

    private final String title;
    private final String content;

    @Builder
    public BoardEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
