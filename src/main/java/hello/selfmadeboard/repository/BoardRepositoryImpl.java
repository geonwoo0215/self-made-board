package hello.selfmadeboard.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.selfmadeboard.controller.form.BoardSearchForm;
import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.domain.QBoard;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Board> getList(BoardSearchForm boardSearchForm) {
        return jpaQueryFactory.selectFrom(QBoard.board)
                .limit(boardSearchForm.getSize())
                .offset((boardSearchForm.getOffset()))
                .orderBy(QBoard.board.id.desc())
                .fetch();
    }
}
