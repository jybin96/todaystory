package com.sparta.todaystory.service;

import com.sparta.todaystory.model.Post;
import com.sparta.todaystory.model.PostDto;
import com.sparta.todaystory.model.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Postservice {
    private final PostRepository postRepository;

    public Post serviceCreatePost(PostDto postDto){
        Post post = new Post(postDto);
        postRepository.save(post);
        return post;
    }

    public Post serviceSearchPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시물을 찾지 못하였습니다.")
        );
        return post;
    }
}
