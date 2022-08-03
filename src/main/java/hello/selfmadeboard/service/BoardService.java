package hello.selfmadeboard.service;

import hello.selfmadeboard.controller.form.BoardForm;
import hello.selfmadeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void write(BoardForm boardForm) {
        log.info("BoardService : 저장!");
        boardRepository.save(boardForm.toBoard());
    }

    public BoardForm read(Long id) {
        log.info("BoardService : 읽기!");
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("없는 글 입니다.")).toBoardForm();

    }


}
