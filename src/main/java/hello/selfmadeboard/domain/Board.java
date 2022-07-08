package hello.selfmadeboard.domain;

import hello.selfmadeboard.controller.dto.BoardForm;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String writer;

    @Column
    private String content;

    @Column
    private Integer view;

    @Builder
    public Board(String title, String writer, String content, Integer view) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.view = view;
    }

    public BoardForm toForm(){
        BoardForm boardForm = BoardForm.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .content(content)
                .build();
        return boardForm;
    }

    public void update(String title, String writer, String content){
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public void updateView(){
        this.view += 1;
    }
}
