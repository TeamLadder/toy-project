package team.radder.toychattingproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.radder.toychattingproject.domain.Comment;
import team.radder.toychattingproject.domain.User;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    //닉네임,내용,작성일시,수정일시
    private String nickname;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CommentUser{
        private Long id;
        private String nickname;
        public CommentUser(User user){
            this.id = user.getId();
            this.nickname = user.getNickname();
        }
    }

    public CommentResponse(Comment comment){
        this.content = comment.getContent();
//        this.nickname = new CommentUser(comment.getUser()).getNickname();  -> 인증객체 적용
        this.created_at = comment.getCreatedAt();
        this.modified_at = comment.getModifiedAt();
    }
}
