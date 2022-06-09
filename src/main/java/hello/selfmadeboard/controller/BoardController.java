package hello.selfmadeboard.controller;

import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String boardList(Model model){
        List<Board> boards = boardService.findBoards();
        model.addAttribute("boardList", boards);
        return "board/boardList";
    }

    @GetMapping("/boardForm")
    public String boardForm(){
        return "board/boardForm";
    }

    @PostMapping("/boardForm")
    public String boardSummit(Board board){
        boardService.save(board);
        return "redirect:/";
    }

    @GetMapping("/boardContent/{id}")
    public String boardContent(@PathVariable("id") Long id, Model model) {
        Board board = boardService.findBoard(id);
        boardService.updateView(id);
        model.addAttribute("board", board);
        return "board/boardContent";
    }

}
