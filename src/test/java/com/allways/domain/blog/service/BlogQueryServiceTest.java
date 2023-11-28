package com.allways.domain.blog.service;

import com.allways.common.factory.blog.BlogFactory;
import com.allways.common.factory.user.UserFactory;
import com.allways.domain.blog.dto.BlogReadResponse;
import com.allways.domain.blog.entity.Blog;
import com.allways.domain.blog.exception.BlogNotFoundException;
import com.allways.domain.blog.repository.BlogRepository;
import com.allways.domain.user.entity.User;
import com.allways.domain.user.exception.UserNotFoundException;
import com.allways.domain.user.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogQueryServiceTest {
    @Mock private BlogRepository blogRepository;
    @Mock private UserRepository userRepository;
    @InjectMocks private BlogQueryService blogQueryService;

    @Test
    void ReadBlogTest() {
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
        BlogReadResponse blogReadResponse = blogQueryService.readBlog(user.getUserSeq());

        // Then
        assertEquals(blog.getBlogName(), blogReadResponse.getBlogName());
        assertEquals(blog.getBlogDescription(), blogReadResponse.getBlogDescription());

        verify(blogRepository, times(1)).findBlogByUserSeq(user.getUserSeq());
        verify(userRepository, times(1)).findUserByUserSeq(user.getUserSeq());
    }

    @Test
    void ReadBlogWhenBlogNotFoundTest() {
        // Given
        Long userSeq = 1L;
        when(blogRepository.findBlogByUserSeq(userSeq)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(BlogNotFoundException.class, () -> blogQueryService.readBlog(userSeq));
        verify(blogRepository, times(1)).findBlogByUserSeq(userSeq);
        verify(userRepository, never()).findUserByUserSeq(anyLong());
    }

    @Test
    void ReadBlogWhenUserNotFoundTest() {
        // Given
        Long userSeq = 9099999L;
        Blog blog = new Blog(
                "Sample Blog",
                "Sample Description",
                userSeq);
        when(blogRepository.findBlogByUserSeq(userSeq)).thenReturn(Optional.of(blog));
        when(userRepository.findUserByUserSeq(userSeq)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(UserNotFoundException.class, () -> blogQueryService.readBlog(userSeq));
        verify(blogRepository, times(1)).findBlogByUserSeq(userSeq);
        verify(userRepository, times(1)).findUserByUserSeq(userSeq);
    }
}
