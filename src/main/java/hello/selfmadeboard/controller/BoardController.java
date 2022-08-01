package hello.selfmadeboard.controller;

import hello.selfmadeboard.controller.form.BoardForm;
import hello.selfmadeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/boards")
    public void board(@RequestBody @Valid BoardForm boardForm, BindingResult bindingResult) {
        log.info("boardForm={}", boardForm.toString());
        boardService.write(boardForm);
    }
}
