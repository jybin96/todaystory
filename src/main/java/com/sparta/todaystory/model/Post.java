package com.sparta.todaystory.model;

import com.sparta.todaystory.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT",nullable = false)
    private String content;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long userId;

    public Post(PostDto postDto, long userId,String name){
        this.title = postDto.getTitle();
        this.name = name;
        this.content = postDto.getContent();
        this.userId = userId;
    }

}
