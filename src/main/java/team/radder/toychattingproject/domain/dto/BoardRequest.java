package team.radder.toychattingproject.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team.radder.toychattingproject.domain.Board;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequest {
    private String title;
    private String content;

    public BoardRequest(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}