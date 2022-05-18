package hello.selfmadeboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String writer;

    @Column
    private String content;

    @Builder
    public Board(Long id, String title, String writer, String content){
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
