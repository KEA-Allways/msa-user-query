package com.allways.domain.blog.service;

import com.allways.domain.blog.dto.BlogReadResponse;
import com.allways.domain.blog.entity.Blog;
import com.allways.domain.blog.exception.BlogNotFoundException;
import com.allways.domain.blog.repository.BlogRepository;
import com.allways.domain.user.entity.User;
import com.allways.domain.user.exception.UserNotFoundException;
import com.allways.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogQueryService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public BlogReadResponse readBlog(Long userSeq){
        Blog blog = blogRepository.findBlogByUserSeq(userSeq).orElseThrow(BlogNotFoundException::new);
        User user = userRepository.findUserByUserSeq(userSeq).orElseThrow(UserNotFoundException::new);

        return BlogReadResponse.toDto(blog, user);
    }
}
