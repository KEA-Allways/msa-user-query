package com.allways.domain.user.dto;

import com.allways.common.factory.blog.BlogFactory;
import com.allways.common.factory.user.UserFactory;
import com.allways.common.factory.user.UserReadResponseFactory;
import com.allways.domain.blog.entity.Blog;
import com.allways.domain.user.entity.User;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserReadResponseTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void userReadResponseValidation() {
        // Given
        UserReadResponse readResponse = UserReadResponseFactory.createUserReadResponse();

        // When
        Set<ConstraintViolation<UserReadResponse>> violations = validator.validate(readResponse);

        // Then
        assertEquals(0, violations.size(), "모든 조건을 만족합니다.");
    }

    @Test
    void userReadUserSeqValidation() {
        // Given
        UserReadResponse readResponse = UserReadResponseFactory.createUserReadResponse(
                null,
                "nickname",
                "blogName",
                1L
        );

        // When
        Set<ConstraintViolation<UserReadResponse>> violations =
                validator.validate(readResponse);

        // Then
        assertEquals(1, violations.size(),
                "userSeq가 null입니다.");
    }

    @Test
    void userReadNicknameValidation() {
        // Given
        UserReadResponse readResponse = UserReadResponseFactory.createUserReadResponse(
                1L,
                "",
                "blogName",
                1L
        );

        // When
        Set<ConstraintViolation<UserReadResponse>> violations =
                validator.validate(readResponse);

        // Then
        assertEquals(1, violations.size(),
                "nickname이 blank입니다.");
    }

    @Test
    void userReadBlogNameValidation() {
        // Given
        UserReadResponse readResponse = UserReadResponseFactory.createUserReadResponse(
                1L,
                "nickname",
                "",
                1L
        );

        // When
        Set<ConstraintViolation<UserReadResponse>> violations =
                validator.validate(readResponse);

        // Then
        assertEquals(1, violations.size(),
                "blogName이 blank입니다.");
    }

    @Test
    void toDtoTest() {
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
}
