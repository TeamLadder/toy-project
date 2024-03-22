package team.radder.toychattingproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import team.radder.toychattingproject.domain.Board;
import team.radder.toychattingproject.domain.User;
import team.radder.toychattingproject.domain.dto.BoardRequest;
import team.radder.toychattingproject.domain.dto.BoardResponse;
import team.radder.toychattingproject.service.BoardService;
import team.radder.toychattingproject.service.UserService;

import java.util.List;

@RestController
@Slf4j
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/api/boards")
    public ResponseEntity<BoardResponse> addBoard(
            @RequestBody BoardRequest request,
            @AuthenticationPrincipal User user
    ) {
        Board board = boardService.save(request, user);
        BoardResponse response = new BoardResponse(board);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/boards")
    public ResponseEntity<List<BoardResponse>> showBoards() {
        List<Board> boardList = boardService.findAll();
        List<BoardResponse> resopnseList = boardList.stream().map(BoardResponse::new).toList();
        return ResponseEntity.ok(resopnseList);
    }

    @GetMapping("/api/boards/{id}")
    public ResponseEntity<BoardResponse> showBoard(
            @PathVariable Long id
    ) {
        Board board = boardService.findById(id);
        BoardResponse response = new BoardResponse(board);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long id,
            @AuthenticationPrincipal User user  // TODO: 인증자만 삭제 가능하도록 만들기
    ) {
        boardService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/boards/{id}")
    public ResponseEntity<BoardResponse> updateBoard(
            @PathVariable Long id,
            @RequestBody BoardRequest request,
            @AuthenticationPrincipal User user  // TODO: 인증자만 수정 가능하도록 만들기
    ) {
        log.info("title: " + request.getTitle());
        log.info("content: " + request.getContent());
        Board board = boardService.update(id, request);
        BoardResponse updated = new BoardResponse(board);
        log.info("new-title: " + updated.getTitle());
        log.info("new-content: " + updated.getContent());
        return ResponseEntity.ok(updated);
    }
}
