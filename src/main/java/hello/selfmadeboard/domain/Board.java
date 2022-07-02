package hello.selfmadeboard.domain;

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
    private int view;

    @Builder
    public Board(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.view = 0;
    }

    public void updateView(){
        this.view += 1;
    }
}
