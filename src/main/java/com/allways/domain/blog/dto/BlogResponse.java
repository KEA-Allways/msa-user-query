package com.allways.domain.blog.dto;

import com.allways.domain.blog.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogResponse {

    private Long blogSeq;

    private String blogName;

    private String blogDescription;

    private String email;

    private String nickname;

    public static BlogResponse toDto(Blog blog){
        return new BlogResponse(blog.getBlogSeq(), blog.getBlogName(), blog.getBlogDescription(), blog.getUser().getEmail(), blog.getUser().getNickname());
    }
}
