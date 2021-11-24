package com.sparta.todaystory.service;

import com.sparta.todaystory.dto.CommentDto;
import com.sparta.todaystory.dto.CommentRequestDto;
import com.sparta.todaystory.model.Comment;
import com.sparta.todaystory.model.User;
import com.sparta.todaystory.repository.CommentRepository;
import com.sparta.todaystory.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getCommentList(Long postId){
        List<Comment> commentList = commentRepository.findAllByPostId(postId);
        return  commentList;
    }

    public void saveComment(CommentDto commentDto, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        Comment comment = new Comment(commentDto,user.getId(),user.getUsername());
        commentRepository.save(comment);
    }

    public boolean updateComment(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        String content = requestDto.getContent();
        comment.setContent(content);
        commentRepository.save(comment);

        return true;
    }

    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }
}
