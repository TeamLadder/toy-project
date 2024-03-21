package team.radder.toychattingproject.controller;

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
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;  // test용. TODO: 나중에 지우기

    public BoardController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    @PostMapping("/api/boards")
    public ResponseEntity<BoardResponse> addBoard(
            @RequestBody BoardRequest request,
            @AuthenticationPrincipal User user
    ) {
        user = userService.findById(1L);    // test용. TODO: 나중에 지우기

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
        return ResponseEntity.ok(board.toResponse());
    }

    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long id
    ) {
        boardService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/boards/{id}")
    public ResponseEntity<BoardResponse> updateBoard(
            @PathVariable Long id,
            @RequestBody BoardRequest request
    ) {
        Board board = boardService.update(id, request);
        BoardResponse updated = new BoardResponse(board);
        return ResponseEntity.ok(updated);
    }
}
