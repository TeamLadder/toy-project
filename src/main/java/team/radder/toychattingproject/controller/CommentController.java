package team.radder.toychattingproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import team.radder.toychattingproject.domain.Comment;
import team.radder.toychattingproject.domain.User;
import team.radder.toychattingproject.dto.CommentRequest;
import team.radder.toychattingproject.dto.CommentResponse;
import team.radder.toychattingproject.service.CommentService;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/boards/{boardId}/comments/")
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentRequest request
                                                        ,@PathVariable Long boardId
                                                        ,@AuthenticationPrincipal User user){
        Comment comment = commentService.saveComment(request,boardId,user);
        CommentResponse response = comment.toResponse();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
    // 가독성+통일성을 위해 boardId 타입 안쓰지만 넣어놨습니다.
    @PutMapping("/api/boards/{boardId}/comments/update/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId
                                                         ,@RequestBody CommentRequest request
                                                         ,@AuthenticationPrincipal User user){

        if(!commentService.isOwnerOfComment(commentId,user)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Comment comment = commentService.updateComment(commentId,request);
        CommentResponse response = comment.toResponse();
        return ResponseEntity.ok(response);

    }
    @DeleteMapping("/api/boards/{boardId}/comments/delete/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId
                                                ,@AuthenticationPrincipal User user){
        if(!commentService.isOwnerOfComment(commentId,user)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

}
