package com.allways.domain.blog.dto;

import com.allways.domain.blog.entity.Blog;
import com.allways.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogReadResponse {

    @NotNull private Long blogSeq;
    @NotBlank private String blogDescription;
    @NotBlank private String blogName;
    @NotBlank private String email;
    @NotBlank private String nickname;

    public static BlogReadResponse toDto(Blog blog, User user){
        return new BlogReadResponse(
                blog.getBlogSeq(),
                blog.getBlogDescription(),
                blog.getBlogName(),
                user.getEmail(),
                user.getNickname());
    }
}
