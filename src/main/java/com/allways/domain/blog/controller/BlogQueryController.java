package com.allways.domain.blog.controller;

import com.allways.common.response.Response;
import com.allways.domain.blog.service.BlogService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BlogQueryController {
    private final BlogService blogService;

    //유저의 블로그 정보 조회
    @GetMapping("/api/blog/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readBlog(@PathVariable Long userSeq){
        return Response.success(blogService.readBlog(userSeq));
    }

}
