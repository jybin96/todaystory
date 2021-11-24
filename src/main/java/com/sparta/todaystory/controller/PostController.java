package com.sparta.todaystory.controller;

import com.sparta.todaystory.model.Post;
import com.sparta.todaystory.dto.PostDto;
import com.sparta.todaystory.repository.CommentRepository;
import com.sparta.todaystory.repository.PostRepository;
import com.sparta.todaystory.security.UserDetailsImpl;
import com.sparta.todaystory.service.Postservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostRepository postRepository;
    private final Postservice postservice;
    private final CommentRepository commentRepository;


    @GetMapping("/api/posts")
    public List<Post> getListPost(){
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")); //생성일 기준으로 정렬한다.
    }

    @DeleteMapping("/api/posts/{id}")
    public boolean deletePosts(@PathVariable Long id){
        commentRepository.deleteAllByPostId(id);
        postRepository.deleteById(id);
        return true;
    }

    @PostMapping("/api/posts")
    @ResponseBody
    public Post createPost(@RequestBody PostDto postDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postservice.serviceCreatePost(postDto,userDetails.getUser().getId());
    }

}
