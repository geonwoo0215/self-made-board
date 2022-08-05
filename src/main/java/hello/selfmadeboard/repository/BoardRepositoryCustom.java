package hello.selfmadeboard.repository;

import hello.selfmadeboard.controller.form.BoardSearchForm;
import hello.selfmadeboard.domain.Board;

import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> getList(BoardSearchForm boardSearchForm);
}
