package com.allways.common.factory.user;

import com.allways.domain.user.dto.UserReadResponse;

public class UserReadResponseFactory {
    public static UserReadResponse createUserReadResponse() {
        return new UserReadResponse(
                1L,
                "nickname",
                "blogName",
                1L);
    }

    public static UserReadResponse createUserReadResponse(
            Long userSeq,
            String nickname,
            String blogName,
            Long blogSeq) {
        return new UserReadResponse(
                userSeq,
                nickname,
                blogName,
                blogSeq);
    }
}
