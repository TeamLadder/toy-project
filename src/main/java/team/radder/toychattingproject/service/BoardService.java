package team.radder.toychattingproject.service;

import org.springframework.stereotype.Service;
import team.radder.toychattingproject.domain.Board;
import team.radder.toychattingproject.domain.User;
import team.radder.toychattingproject.domain.dto.BoardRequest;
import team.radder.toychattingproject.repository.BoardRepository;

import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board save(BoardRequest request, User user) {
        return boardRepository.save(new Board(request, user));
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board findById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found id" + id));
    }

    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

    public Board update(Long id, BoardRequest request) {
        Board updated = findById(id);
        updated.update(request.getTitle(), request.getContent());
        return updated;
    }
}
