package com.sparta.todaystory.repository;

import com.sparta.todaystory.model.Comment;
import com.sparta.todaystory.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostId(Long postId);

    @Transactional
    void deleteAllByPostId(Long postId);
}
