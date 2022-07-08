package hello.selfmadeboard.service;

import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(Board board) {
        Long id = boardRepository.save(board);
        return id;
    }

    public Board findBoard(Long id){
        Board board = boardRepository.findById(id);
        return board;
    }

    public List<Board> findBoards() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    @Transactional
    public void updateBoard(Long id, String title, String writer, String content){
        Board board = boardRepository.findById(id);
        board.update(title,writer,content);
    }

    @Transactional
    public void updateView(Long id){
        Board board = boardRepository.findById(id);
        board.updateView();
    }

    @Transactional
    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }


    public List<Board> searchByTitle(String title) {
        List<Board> boardList = boardRepository.searchByTitle(title);
        return boardList;
    }

}