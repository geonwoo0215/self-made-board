package hello.selfmadeboard.service;

import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;

    @Transactional
    public Long save(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

}
