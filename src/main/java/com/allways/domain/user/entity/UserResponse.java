package com.allways.domain.user.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long userSeq;
    private String nickname;
    private String blogName;
    private String password;
    private String profileUrl;

    public static UserResponse toDto(User user) {
        return new UserResponse(user.getUserSeq(), user.getNickname()
                , (user.getBlog() != null) ? user.getBlog().getBlogName() : "",user.getProfileImgSeq(),user.getPassword());

    }
}
