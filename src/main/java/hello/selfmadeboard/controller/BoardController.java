package hello.selfmadeboard.controller;

import hello.selfmadeboard.controller.form.BoardForm;
import hello.selfmadeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public void post(@Valid @RequestBody BoardForm boardForm) {
        log.info("boardForm={}", boardForm.toString());
        boardService.write(boardForm);
    }

    @GetMapping("/board/{boardId}")
    public BoardForm get(@PathVariable(name = "boardId") Long id) {
        log.info("boardId={}", id);
        return boardService.read(id);
    }
}
