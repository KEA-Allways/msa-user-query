package com.allways.domain.blog.controller;

import com.allways.common.response.Response;
import com.allways.domain.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    //유저의 블로그 정보 조회
    @GetMapping("/api/blogs/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readBlogInfo(@PathVariable Long userSeq){
        return Response.success(blogService.readBlogInfo(userSeq));
    }
}
