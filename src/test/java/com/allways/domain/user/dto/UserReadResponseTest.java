package com.allways.domain.user.dto;

import com.allways.common.factory.blog.BlogFactory;
import com.allways.common.factory.user.UserFactory;

import com.allways.domain.blog.entity.Blog;
import com.allways.domain.user.entity.User;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserReadResponseTest {

    @Test
    void testToDto() {
        // Given
        User user = UserFactory.createUser();
        Blog blog = BlogFactory.createBlog();

        // When
        UserReadResponse userReadResponse = UserReadResponse.toDto(user, blog);

        // Then
        assertNotNull(userReadResponse);
        assertEquals(user.getUserSeq(), userReadResponse.getUserSeq());
        assertEquals(user.getNickname(), userReadResponse.getNickname());
        assertEquals(blog.getBlogSeq(), userReadResponse.getBlogSeq());
        assertEquals(blog.getBlogName(), userReadResponse.getBlogName());
    }

    @Test
    void testToDtoWithNullBlog() {
        // Given
        User user = UserFactory.createUser();

        // When
        UserReadResponse userReadResponse = UserReadResponse.toDto(user, null);

        // Then
        assertNotNull(userReadResponse);
        assertEquals(user.getUserSeq(), userReadResponse.getUserSeq());
        assertEquals(user.getNickname(), userReadResponse.getNickname());

        // 만들어진 블로그가 없을 시 블로그 이름은 null 값으로 지정
        assertNull(userReadResponse.getBlogName());
        assertNull(userReadResponse.getUserSeq());
    }
}
