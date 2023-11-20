package com.allways.domain.blog.dto;

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
}
