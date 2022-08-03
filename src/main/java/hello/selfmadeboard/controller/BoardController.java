package hello.selfmadeboard.controller;

import hello.selfmadeboard.controller.form.BoardRequestForm;
import hello.selfmadeboard.controller.form.BoardResponseForm;
import hello.selfmadeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public void post(@Valid @RequestBody BoardRequestForm boardForm) {
        log.info("boardForm={}", boardForm.toString());
        boardService.write(boardForm);
    }

    @GetMapping("/board/{boardId}")
    public BoardResponseForm get(@PathVariable(name = "boardId") Long id) {
        log.info("boardId={}", id);
        return boardService.read(id);
    }

    @GetMapping("/board")
    public List<BoardResponseForm> getList() {
        return boardService.list();
    }
}
