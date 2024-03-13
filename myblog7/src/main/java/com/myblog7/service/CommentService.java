package com.myblog7.service;

import com.myblog7.entity.Comment;
import com.myblog7.payload.CommentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentsById(long postId, long commentId);


    List<CommentDto> getAllComments();

    void deleteCommentById(long postId, long commentId);
}
