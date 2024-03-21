package team.radder.toychattingproject.dto;

import lombok.Builder;
import lombok.Getter;
import team.radder.toychattingproject.domain.Board;
import team.radder.toychattingproject.domain.Comment;
import team.radder.toychattingproject.domain.User;

@Getter
@Builder
public class CommentRequest {
    //내용, 작성일시,수정일시,게시판 아이디, 유저 아이디
    private String content;

    public Comment toEntity(Board board,User user){
        return Comment.builder()
                .content(content)
                .user(user)
                .board(board)
                .build();
    }
}
