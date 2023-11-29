package com.allways.domain.user.dto;

import com.allways.domain.blog.entity.Blog;
import com.allways.domain.user.entity.User;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReadResponse {
    @NotNull private Long userSeq;
    @NotBlank private String nickname;
    @NotBlank private String blogName;
    private Long blogSeq;

    // blog가 null이 아닐 경우 즉 해당 유저가 블로그를 가지고 있을 경우 blogName 을 담아서 보낸다
    public static UserReadResponse toDto(User user, Blog blog) {
        return new UserReadResponse(
                user.getUserSeq(),
                user.getNickname(),
                (blog != null) ? blog.getBlogName() : null,
                (blog != null) ? blog.getBlogSeq() : null
        );
    }
}
