package com.sparta.todaystory.controller;

import com.sparta.todaystory.model.Post;
import com.sparta.todaystory.model.PostDto;
import com.sparta.todaystory.model.PostRepository;
import com.sparta.todaystory.service.Postservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostRepository postRepository;
    private final Postservice postservice;

    @GetMapping("/api/posts")
    public List<Post> getListPost(){
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")); //생성일 기준으로 정렬한다.
    }
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostDto postDto){
        return postservice.serviceCreatePost(postDto);
    }

}
