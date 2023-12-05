package com.allways.domain.user.service;

import com.allways.common.factory.blog.BlogFactory;
import com.allways.common.factory.user.UserFactory;
import com.allways.domain.blog.entity.Blog;
import com.allways.domain.user.entity.User;
import com.allways.domain.user.dto.UserReadResponse;
import com.allways.domain.user.repository.UserRepository;
import com.allways.domain.blog.repository.BlogRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock private UserRepository userRepository;
    @Mock private BlogRepository blogRepository;
    @InjectMocks private UserService userService;

    @Test
    void readUserByUserSeqTest() {
        // Given
        User user = UserFactory.createUser();
        Blog blog = BlogFactory.createBlog();
        // 블로그의 Seq는 넣어주는 값이 아니라 자동으로 만들어지는 값인데 테스트과정에서는 블로그를 새로 만드는 과정이 없으니
        // 블로그의 Seq를 직접 넣어주는 과정이 필요하다.
        blog.setBlogSeq(1L);

        when(userRepository.findUserByUserSeq(user.getUserSeq())).thenReturn(Optional.of(user));
        when(blogRepository.findBlogByUserSeq(user.getUserSeq())).thenReturn(Optional.of(blog));

        // When
        // UserReadResponse 는 userSeq, nickname, blogName 만을 가지고 있다.
        UserReadResponse userReadResponse = userService.readUserBySeq(user.getUserSeq());

        // Then
        assertNotNull(userReadResponse);
        assertEquals(user.getUserSeq(), userReadResponse.getUserSeq());
        assertEquals(user.getNickname(), userReadResponse.getNickname());
        assertEquals(blog.getBlogSeq(), userReadResponse.getBlogSeq());
        assertEquals(blog.getBlogName(), userReadResponse.getBlogName());
    }

    @Test
    void readUserByIdTest() {
        // Given
        User user = UserFactory.createUser();
        Blog blog = BlogFactory.createBlog();
        blog.setBlogSeq(1L);

        when(userRepository.findUserByUserId(user.getUserId())).thenReturn(Optional.of(user));
        when(blogRepository.findBlogByUserSeq(user.getUserSeq())).thenReturn(Optional.of(blog));

        // When
        UserReadResponse userReadResponse = userService.readUserById(user.getUserId());

        // Then
        assertNotNull(userReadResponse);
        assertEquals(user.getUserSeq(), userReadResponse.getUserSeq());
        assertEquals(user.getNickname(), userReadResponse.getNickname());
        assertEquals(blog.getBlogSeq(), userReadResponse.getBlogSeq());
        assertEquals(blog.getBlogName(), userReadResponse.getBlogName());
    }
}
