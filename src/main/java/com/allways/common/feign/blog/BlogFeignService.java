package com.allways.common.feign.blog;

import com.allways.domain.blog.entity.Blog;
import com.allways.domain.blog.exception.BlogNotFoundException;
import com.allways.domain.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogFeignService {

    private final BlogRepository blogRepository;

    public BlogFeignResponse queryBlog(Long userSeq) {
        Blog blog = blogRepository.findBlogByUserSeq(userSeq).orElseThrow(BlogNotFoundException::new);
        return new BlogFeignResponse(blog.getBlogSeq(), blog.getBlogName(), blog.getBlogDescription());
    }

}