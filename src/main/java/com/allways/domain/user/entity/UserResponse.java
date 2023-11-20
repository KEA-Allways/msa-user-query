package com.allways.domain.user.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long userSeq;
    private String nickname;
    private String blogName;

    public static UserResponse toDto(User user) {
        return new UserResponse(user.getUserSeq(), user.getNickname()
                , (user.getBlog() != null) ? user.getBlog().getBlogName() : "");
    }
}
