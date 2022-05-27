package hello.selfmadeboard.controller;

import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String boardList(Model model){
        List<Board> boards = boardService.findBoards();
        model.addAttribute("boards", boards);
        return "board/boardList";
    }

}
