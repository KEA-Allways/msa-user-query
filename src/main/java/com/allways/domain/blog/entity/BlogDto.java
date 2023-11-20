package com.allways.domain.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDto {

    private Long blogSeq;
    private String blogName;
    private String blogDescription;
    private String email;
    private String nickname;

    public static BlogDto toDto(Blog blog){
        return new BlogDto(blog.getBlogSeq(), blog.getBlogName(), blog.getBlogDescription(), blog.getUser().getEmail(), blog.getUser().getNickname());
    }
}
