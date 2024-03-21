package team.radder.toychattingproject.service;

import jakarta.transaction.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import team.radder.toychattingproject.domain.Board;
import team.radder.toychattingproject.domain.Comment;
import team.radder.toychattingproject.domain.User;
import team.radder.toychattingproject.dto.CommentRequest;
import team.radder.toychattingproject.repository.BoardRepository;
import team.radder.toychattingproject.repository.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public CommentService(CommentRepository commentRepository, BoardRepository boardRepository) {
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
    }
    public boolean isOwnerOfComment(Long commentId, User user){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new IllegalArgumentException("comment doesn't exist"));
        User owner = comment.getUser();
        return user == owner;
    }
    public Comment saveComment( CommentRequest dto,Long boardId,User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("board doesn't exist"));
        Comment comment = dto.toEntity(board,user);
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(Long commentId,CommentRequest dto){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new IllegalArgumentException("comment doesn't exist"));
        String content = dto.getContent();
        commentRepository.updateComment(commentId,content);
        return comment;
    }
    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }
    // 댓글 전체 삭제
    public void deleteAllComment(Long boardId){
        commentRepository.deleteAllByBoardId(boardId);
    }
}
