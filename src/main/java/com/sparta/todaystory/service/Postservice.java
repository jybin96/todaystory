package com.sparta.todaystory.service;

import com.sparta.todaystory.model.Post;
import com.sparta.todaystory.dto.PostDto;
import com.sparta.todaystory.model.User;
import com.sparta.todaystory.repository.PostRepository;
import com.sparta.todaystory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Postservice {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post serviceCreatePost(PostDto postDto,Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("찾지 못하였습니다.")
        );

        Post post = new Post(postDto,userId,user.getUsername());
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
