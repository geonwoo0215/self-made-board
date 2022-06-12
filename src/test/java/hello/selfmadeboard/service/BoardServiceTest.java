package hello.selfmadeboard.service;

import hello.selfmadeboard.domain.Board;
import hello.selfmadeboard.repository.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

//    @Autowired
//    BoardService boardService;
//
//    @Autowired
//    BoardRepository boardRepository;
//
//
//    @Test
//    @Transactional
//    void save() {
//        Board board = Board.builder()
//                .writer("LEE")
//                .title("hi")
//                .content("hello")
//                .build();
//
//        Long id = boardService.save(board);
//
//        Assertions.assertEquals(board,boardRepository.findById(id));
//    }
}