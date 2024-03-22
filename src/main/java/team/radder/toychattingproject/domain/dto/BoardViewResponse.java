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
public class BoardViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BoardViewResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getModifiedAt();
    }
}
