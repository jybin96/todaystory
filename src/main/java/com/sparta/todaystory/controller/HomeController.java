package com.sparta.todaystory.controller;

import com.sparta.todaystory.model.Comment;
import com.sparta.todaystory.model.Post;
import com.sparta.todaystory.repository.CommentRepository;
import com.sparta.todaystory.repository.PostRepository;
import com.sparta.todaystory.security.UserDetailsImpl;
import com.sparta.todaystory.service.Postservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final Postservice postservice;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetailsImpl userDetails,Model model) {
        List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        model.addAttribute("postList",postList);
        if(userDetails == null){
            model.addAttribute("islogin",false);
            return "index";
        }
        model.addAttribute("islogin", true);
        return "index";
    }

    @GetMapping("/posts/{id}")
    public String updateProduct(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long id ,Model model) {
        List<Comment> commentList = commentRepository.findAllByPostId(id);
        Collections.reverse(commentList);
        Post post = postservice.serviceSearchPost(id);
        if(userDetails != null){
            model.addAttribute("userId",userDetails.getUser().getId());
        }else{
            model.addAttribute("userId",null);
        }
        model.addAttribute("post",post);
        model.addAttribute("commentList",commentList);
// 응답 보내기 (업데이트된 상품 id)
        return "post";
    }
}


