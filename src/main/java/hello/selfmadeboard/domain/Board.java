package hello.selfmadeboard.domain;

import hello.selfmadeboard.controller.form.BoardRequestForm;
import hello.selfmadeboard.controller.form.BoardResponseForm;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;

    @Builder
    public Board(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public BoardResponseForm toBoardResponseForm() {
        return BoardResponseForm.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }

}
