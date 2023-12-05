package com.allways.common.feign.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserFeignResponse {
    private Long userSeq;
    private String userId;
    private String nickname;
    private String email;
    private String password;
    private String profileImgSeq;
}
