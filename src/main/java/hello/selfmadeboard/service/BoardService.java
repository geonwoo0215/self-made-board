package hello.selfmadeboard.service;

import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
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
    @Modifying
    public void updateView(Long id) {
        boardRepository.updateView(id);
    }

    @Transactional
    public void deleteById(Long id){
        boardRepository.deleteById(id);
    }

}
