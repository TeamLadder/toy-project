package team.radder.toychattingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.radder.toychattingproject.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Modifying
    @Query("update Comment set content = :content where id = :id")
    void updateComment(Long id, String content);

    void deleteAllByBoardId(Long boardId);

}
