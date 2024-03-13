package com.myblog7.controller;

import com.myblog7.payload.CommentDto;
import com.myblog7.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //http://localhost:8080/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createCommnet(@PathVariable("postId") long postId, @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);//201
    }

    //http://localhost:8080/api/posts/1/comments
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable("postId") long postId){
      return commentService.getCommentsByPostId(postId);

    }

    //http://localhost:8080/api/posts/1/comments/1  Develop for a particular post get a particular comment
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public CommentDto getCommentsById(@PathVariable("postId") long postId,
                                          @PathVariable("commentId") long commentId){
        return commentService.getCommentsById(postId, commentId);
    }

    //http://localhost:8080/api/comments
    @GetMapping("/comments")
     public List<CommentDto> getAllComments(){
        return commentService.getAllComments();
     }


    //http://localhost:8080/posts/1/comments/1
     @DeleteMapping("/posts/{postId}/comments/{commentId}")
     public ResponseEntity<String> deleteCommentById(@PathVariable("postId") long postId,
                                     @PathVariable("commentId") long commentId){
        commentService.deleteCommentById(postId,commentId);
       return new ResponseEntity<>("Ankesh Cool Comment is delete",HttpStatus.OK);
     }
}


