package hello.selfmadeboard.service;

import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.dto.BoardDto;
import hello.selfmadeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardDto boardDto) {
        boardRepository.save(boardDto.toEntity());
        return boardDto.getId();
    }

//    @Transactional
//    public Board findBoard(Long id){
//        return boardRepository.findById(id);
//    }

    @Transactional
    public BoardDto findBoard(Long id){
        Board board = boardRepository.findById(id);

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .writer(board.getWriter())
                .title(board.getTitle())
                .content(board.getContent())
                .view(board.getView())
                .build();
        return boardDto;
    }

    @Transactional
    public List<BoardDto> findBoards() {

        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board: boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .writer(board.getWriter())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .view(board.getView())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public void updateView(Long id) {
        boardRepository.updateView(id);
    }

    @Transactional
    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void updateBoard(Long id, String title, String writer, String content){
        Board board = boardRepository.findById(id);
        board.setTitle(title);
        board.setContent(content);
        board.setWriter(writer);
    }

    @Transactional
    public List<BoardDto> searchByTitle(String title) {
        List<Board> boardList = boardRepository.searchByTitle(title);
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board: boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .writer(board.getWriter())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .view(board.getView())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

}
