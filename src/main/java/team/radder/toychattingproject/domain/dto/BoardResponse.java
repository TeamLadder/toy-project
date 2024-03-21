package team.radder.toychattingproject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.radder.toychattingproject.domain.Board;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardResponse {
    private Long id;
    private String title;
    private String content;
    private String nickname;    // 작성자
    private LocalDateTime created_at;

    public BoardResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.created_at = board.getCreatedAt();
        this.nickname = board.getUser().getNickname();
    }
}
