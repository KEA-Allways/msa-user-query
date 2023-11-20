package com.allways.domain.blog.service;

import com.allways.domain.blog.entity.Blog;
import com.allways.domain.blog.entity.BlogDto;
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

    public BlogDto readBlog(Long userSeq){
        Blog blog = blogRepository.findBlogByUserSeq(userSeq).orElseThrow(BlogNotFoundException::new);

        return BlogDto.toDto(blog);
    }
}
