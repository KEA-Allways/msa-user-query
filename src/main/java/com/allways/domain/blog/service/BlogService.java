package com.allways.domain.blog.service;

import com.allways.common.feign.blog.BlogFeignResponse;
import com.allways.domain.blog.dto.BlogResponse;
import com.allways.domain.blog.entity.Blog;
import com.allways.domain.blog.exception.BlogNotFoundException;
import com.allways.domain.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogResponse readBlogInfo(Long userSeq) {
        Blog blog = blogRepository.findBlogInfoByUserSeq(userSeq).orElseThrow(BlogNotFoundException::new);
        return BlogResponse.toDto(blog);
    }
}
