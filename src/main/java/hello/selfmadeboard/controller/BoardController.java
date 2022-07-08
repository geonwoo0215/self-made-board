package hello.selfmadeboard.controller;

import hello.selfmadeboard.controller.dto.BoardDto;
import hello.selfmadeboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String boardList(Model model){
        List<BoardDto> boards = boardService.findBoards().stream().map((b)->b.toDto()).collect(Collectors.toCollection(ArrayList::new));
        model.addAttribute("boardList", boards);
        return "board/boardList";
    }

    @GetMapping("/boardForm")
    public String boardForm(Model model)
    {
        model.addAttribute("boardDto", BoardDto.builder().build());
        return "board/boardForm";
    }

    @PostMapping("/boardForm")
    public String boardSummit(@Validated @ModelAttribute("boardDto") BoardDto boardDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "board/boardForm";
        }

        boardService.save(boardDto.toEntity());
        return "redirect:/";
    }

    @GetMapping("/boardContent/{id}")
    public String boardContent(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.findBoard(id).toDto();
        boardService.updateView(id);
        model.addAttribute("boardDto", boardDto);
        return "board/boardContent";
    }

    @DeleteMapping("/boardContent/{id}")
    public String boardDelete(@PathVariable("id") Long id){
        boardService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/boardContent/{id}/edit")
    public String boardUpdateForm(@PathVariable("id") Long id, Model model){
        BoardDto boardDto = boardService.findBoard(id).toDto();
        model.addAttribute("boardDto", boardDto);
        return "board/boardEdit";
    }

    @PostMapping("/boardContent/{id}/edit")
    public String boardUpdate(@PathVariable("id") Long id, @ModelAttribute("boardDto") BoardDto boardDto){
        boardService.updateBoard(id,boardDto.getTitle(),boardDto.getWriter(),boardDto.getContent());
        return "redirect:/";
    }

    @GetMapping("/boardSearch")
    public String boardSearch(@RequestParam(value="keyword") String title, Model model){
        List<BoardDto> boardList = boardService.searchByTitle(title).stream().map((b)->b.toDto()).collect(Collectors.toCollection(ArrayList::new));;
        model.addAttribute("boardList", boardList);
        return "board/boardList";
    }

}
