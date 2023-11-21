package com.allways.common.feign.blog;

import com.allways.domain.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BlogFeignController {

    private final BlogFeignService blogFeignService;

    //유저의 블로그 정보 조회
    @GetMapping("/api/blogs/feign/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public BlogFeignResponse queryBlog(@PathVariable Long userSeq){
        return blogFeignService.queryBlog(userSeq);

    }
}
