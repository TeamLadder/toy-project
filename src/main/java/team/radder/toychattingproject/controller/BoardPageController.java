package team.radder.toychattingproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import team.radder.toychattingproject.domain.Board;
import team.radder.toychattingproject.domain.dto.BoardViewResponse;
import team.radder.toychattingproject.service.BoardService;

import java.util.List;

@Controller
public class BoardPageController {
    private final BoardService boardService;

    public BoardPageController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boards")
    public String showBoards(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "noticeboard";
    }

    @GetMapping("/boards/{id}")
    public String showBoard(
            Model model,
            @PathVariable Long id
    ) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "boardDetail";
    }

    @GetMapping("/boards/new-board")
    public String newBoard(
            Model model,
            @RequestParam(required = false) Long id
    ) {
        if (id == null) {  // 등록
            model.addAttribute("board", new BoardViewResponse());
        } else {  // 수정
            Board board = boardService.findById(id);
            model.addAttribute("board", new BoardViewResponse(board));
        }
        return "newBoard";
    }
}
