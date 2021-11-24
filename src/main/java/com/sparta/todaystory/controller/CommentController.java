package com.sparta.todaystory.controller;

import com.sparta.todaystory.dto.CommentDto;
import com.sparta.todaystory.dto.CommentRequestDto;
import com.sparta.todaystory.security.UserDetailsImpl;
import com.sparta.todaystory.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/comments")
    public boolean saveComments(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentDto commentDto){
        if(userDetails==null){
            return false;
        }
        commentService.saveComment(commentDto,userDetails);
        return true;
    }

    @PutMapping("/api/comments/{id}")
    public boolean updateComments(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(id,requestDto);
    }

    @DeleteMapping("/api/comments/{id}")
    public boolean deleteComments(@PathVariable Long id){
        commentService.deleteComment(id);
        return true;
    }
}
