package com.allways.domain.blog.service;

import com.allways.common.factory.blog.BlogFactory;
import com.allways.common.factory.user.UserFactory;
import com.allways.domain.blog.dto.BlogReadResponse;
import com.allways.domain.blog.entity.Blog;
import com.allways.domain.blog.repository.BlogRepository;
import com.allways.domain.user.entity.User;
import com.allways.domain.user.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {
    @Mock private BlogRepository blogRepository;
    @Mock private UserRepository userRepository;
    @InjectMocks private BlogService blogService;

    @Test
    void readBlogTest() {
        // Given
        User user = UserFactory.createUser();
        Blog blog = BlogFactory.createBlog(
                "newBlogName",
                "newBlogDescription",
                user.getUserSeq()
        );

        when(blogRepository.findBlogByUserSeq(user.getUserSeq())).thenReturn(Optional.of(blog));
        when(userRepository.findUserByUserSeq(user.getUserSeq())).thenReturn(Optional.of(user));

        // When
        BlogReadResponse blogReadResponse = blogService.readBlog(user.getUserSeq());

        // Then
        assertEquals(blog.getBlogName(), blogReadResponse.getBlogName());
        assertEquals(blog.getBlogDescription(), blogReadResponse.getBlogDescription());

        verify(blogRepository).findBlogByUserSeq(user.getUserSeq());
        verify(userRepository).findUserByUserSeq(user.getUserSeq());
    }
}
