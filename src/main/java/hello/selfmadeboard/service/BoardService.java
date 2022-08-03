package hello.selfmadeboard.service;

import hello.selfmadeboard.controller.form.BoardRequestForm;
import hello.selfmadeboard.controller.form.BoardResponseForm;
import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new IllegalArgumentException("없는 글 입니다.")).toBoardResponseForm();

    }

    public List<BoardResponseForm> list(){
        log.info("BoardService : 전체 글 조회");
        return boardRepository.findAll().stream().map(Board::toBoardResponseForm).collect(Collectors.toList());
    }




}
