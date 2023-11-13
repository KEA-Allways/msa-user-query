package com.allways.domain.user.domain;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long userSeq;

    private String nickname;

    private String blogName;

    public static UserDto toDto(User user) {
        return new UserDto(user.getUserSeq(), user.getNickname()
                , (user.getBlog() != null) ? user.getBlog().getBlogName() : "");
    }

}
