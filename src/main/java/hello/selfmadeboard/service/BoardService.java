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


}
