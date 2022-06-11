package hello.selfmadeboard.service;

import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    @Transactional
    public Board findBoard(Long id){
        return boardRepository.findById(id);
    }

    @Transactional
    public List<Board> findBoards() {
        return boardRepository.findAll();
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
    public List<Board> searchByTitle(String title) {
        List<Board> boardList = boardRepository.searchByTitle(title);
        return boardList;
    }

}
