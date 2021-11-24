package com.sparta.todaystory.model;

import com.sparta.todaystory.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(columnDefinition = "LONGTEXT",nullable = false)
    private String content;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long postId;

    public Comment(CommentDto commentDto, Long userId, String name){
        this.name = name;
        this.content = commentDto.getContent();
        this.userId = userId;
        this.postId = commentDto.getPostId();
    }
}
