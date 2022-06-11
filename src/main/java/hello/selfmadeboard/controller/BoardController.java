package hello.selfmadeboard.controller;

import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/boardContent/{id}")
    public String boardDelete(@PathVariable("id") Long id){
        boardService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/boardContent/{id}/edit")
    public String boardUpdateForm(@PathVariable("id") Long id, Model model){
        Board board = boardService.findBoard(id);

        model.addAttribute("board", board);
        return "board/boardEdit";
    }

    @PostMapping("/boardContent/{id}/edit")
    public String boardUpdate(@PathVariable("id") Long id, @ModelAttribute("board") Board board){
        boardService.updateBoard(id,board.getTitle(),board.getWriter(),board.getContent());
        return "redirect:/";
    }

    @GetMapping("/boardSearch")
    public String boardSearch(String title, Model model){
        List<Board> boardList = boardService.searchByTitle(title);

        model.addAttribute("searchList", boardList);

        return "board/boardList";
    }

}
