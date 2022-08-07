package hello.selfmadeboard.service;

import hello.selfmadeboard.controller.form.BoardEditForm;
import hello.selfmadeboard.controller.form.BoardRequestForm;
import hello.selfmadeboard.controller.form.BoardResponseForm;
import hello.selfmadeboard.controller.form.BoardSearchForm;
import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.domain.BoardEditor;
import hello.selfmadeboard.exception.BoardNotFound;
import hello.selfmadeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void write(BoardRequestForm boardForm) {
        log.info("BoardService : 저장!");
        boardRepository.save(boardForm.toBoard());
    }

    public BoardResponseForm read(Long id) {
        log.info("BoardService : 읽기!");
        return boardRepository.findById(id)
                .orElseThrow(BoardNotFound::new).toBoardResponseForm();

    }

    public List<BoardResponseForm> list(BoardSearchForm boardSearchForm){
        log.info("BoardService : 전체 글 조회");

        return boardRepository.getList(boardSearchForm).stream().map(Board::toBoardResponseForm).collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, BoardEditForm boardEditForm) {
        Board board = boardRepository.findById(id)
                .orElseThrow(BoardNotFound::new);

        BoardEditor.BoardEditorBuilder boardEditorBuilder = board.toEditor();

        BoardEditor boardEditor = boardEditorBuilder
                .title(boardEditForm.getTitle())
                .content(boardEditForm.getContent())
                .build();

        board.edit(boardEditor);

    }

    public void delete(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(BoardNotFound::new);

        boardRepository.delete(board);

    }


}
