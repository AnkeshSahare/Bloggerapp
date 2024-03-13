package com.myblog7.repository;

import com.myblog7.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

//custom method for RepositoryLayer find by column name and hibernate write sql query automatically.
// select * from comment where post_id=1
    List<Comment> findByPostId(long postId);
}
