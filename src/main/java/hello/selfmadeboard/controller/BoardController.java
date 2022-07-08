package hello.selfmadeboard.controller;

import hello.selfmadeboard.controller.Form.BoardForm;
import hello.selfmadeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String boardList(Model model){
        List<BoardForm> boards = boardService.findBoards().stream().map((b)->b.toForm()).collect(Collectors.toCollection(LinkedList::new));
        model.addAttribute("boardList", boards);
        return "board/boardList";
    }

    @GetMapping("/boardForm")
    public String boardForm(Model model)
    {
        model.addAttribute("boardDto", BoardForm.builder().build());
        return "board/boardForm";
    }

    @PostMapping("/boardForm")
    public String boardSummit(@Validated @ModelAttribute("boardDto") BoardForm boardForm, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "board/boardForm";
        }

        boardService.save(boardForm.toEntity());
        return "redirect:/";
    }

    @GetMapping("/boardContent/{id}")
    public String boardContent(@PathVariable("id") Long id, Model model) {
        BoardForm boardForm = boardService.findBoard(id).toForm();
        boardService.updateView(id);
        model.addAttribute("boardForm", boardForm);
        return "board/boardContent";
    }

    @DeleteMapping("/boardContent/{id}")
    public String boardDelete(@PathVariable("id") Long id){
        boardService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/boardContent/{id}/edit")
    public String boardUpdateForm(@PathVariable("id") Long id, Model model){
        BoardForm boardForm = boardService.findBoard(id).toForm();
        model.addAttribute("boardDto", boardForm);
        return "board/boardEdit";
    }

    @PostMapping("/boardContent/{id}/edit")
    public String boardUpdate(@PathVariable("id") Long id, @ModelAttribute("boardDto") BoardForm boardForm){
        boardService.updateBoard(id,boardForm.getTitle(),boardForm.getWriter(),boardForm.getContent());
        return "redirect:/";
    }

    @GetMapping("/boardSearch")
    public String boardSearch(@RequestParam(value="keyword") String title, Model model){
        List<BoardForm> boardList = boardService.searchByTitle(title).stream().map((b)->b.toForm()).collect(Collectors.toCollection(ArrayList::new));;
        model.addAttribute("boardList", boardList);
        return "board/boardList";
    }

}
