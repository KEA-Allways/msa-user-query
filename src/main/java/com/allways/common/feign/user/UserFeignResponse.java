package com.allways.common.feign.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Getter
public class UserFeignResponse {

    private String userId;
    private String nickname;
    private String email;
    private String profileImgSeq;
}
