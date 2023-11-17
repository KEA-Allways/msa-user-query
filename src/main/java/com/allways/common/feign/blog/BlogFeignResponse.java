package com.allways.common.feign.blog;

import com.allways.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Getter
@AllArgsConstructor
public class BlogFeignResponse {

    private Long blogSeq;
    private String blogName;
    private String blogDescription;


}
