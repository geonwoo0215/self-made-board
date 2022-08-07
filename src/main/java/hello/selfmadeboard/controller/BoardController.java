package hello.selfmadeboard.controller;

import hello.selfmadeboard.controller.form.BoardEditForm;
import hello.selfmadeboard.controller.form.BoardRequestForm;
import hello.selfmadeboard.controller.form.BoardResponseForm;
import hello.selfmadeboard.controller.form.BoardSearchForm;
import hello.selfmadeboard.exception.InvalidRequest;
import hello.selfmadeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

        boardForm.validate();
        boardService.write(boardForm);
    }

    @GetMapping("/board/{boardId}")
    public BoardResponseForm get(@PathVariable(name = "boardId") Long id) {
        log.info("boardId={}", id);
        return boardService.read(id);
    }

    @GetMapping("/board")
    public List<BoardResponseForm> getList(@ModelAttribute BoardSearchForm boardSearchForm) {
        return boardService.list(boardSearchForm);
    }

    @PatchMapping("/board/{boardId}")
    public void edit(@PathVariable Long boardId, @RequestBody @Valid BoardEditForm request) {
        boardService.edit(boardId, request);
    }

    @DeleteMapping("/board/{boardId}")
    public void delete(@PathVariable Long boardId) {
        boardService.delete(boardId);
    }

}
