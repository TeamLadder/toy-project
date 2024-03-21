package team.radder.toychattingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.radder.toychattingproject.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
