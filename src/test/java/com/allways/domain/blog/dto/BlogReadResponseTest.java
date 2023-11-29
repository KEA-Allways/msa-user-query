package com.allways.domain.blog.dto;

import com.allways.common.factory.blog.BlogReadResponseFactory;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlogReadResponseTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void blogReadResponseValidation() {
        // Given
        BlogReadResponse response = BlogReadResponseFactory.createBlogReadResponse();

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations =
                validator.validate(response);

        // Then
        assertEquals(0, violations.size(),
                "위반 사항이 없습니다.");
    }

    @Test
    void blogReadBlogSeqValidation() {
        // Given
        BlogReadResponse response = new BlogReadResponse(
                null,
                "blogDescription",
                "blogName",
                "email@email.com",
                "nickname"
        );

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations =
                validator.validate(response);

        // Then
        assertEquals(1, violations.size(),
                "blogSeq가 존재하지 않습니다.");
    }

    @Test
    void blogReadBlogDescriptionValidation() {
        // Given
        BlogReadResponse response = new BlogReadResponse(
                1L,
                "",
                "blogName",
                "email@email.com",
                "nickname"
        );

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations =
                validator.validate(response);

        // Then
        assertEquals(1, violations.size(),
                "blogDescription이 존재하지 않습니다.");
    }

    @Test
    void blogReadBlogNameValidation() {
        // Given
        BlogReadResponse response = new BlogReadResponse(
                1L,
                "blogDescription",
                "",
                "email@email.com",
                "nickname"
        );

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations =
                validator.validate(response);

        // Then
        assertEquals(1, violations.size(),
                "blogName이 존재하지 않습니다.");
    }

    @Test
    void blogReadEmailValidation() {
        // Given
        BlogReadResponse response = new BlogReadResponse(
                1L,
                "blogName",
                "blogDescription",
                null,
                "nickname"
        );

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations =
                validator.validate(response);

        // Then
        assertEquals(1, violations.size(),
                "Email이 존재하지 않습니다."); // Expecting 5 violations for 5 @NotBlank fields
    }

    @Test
    void blogReadNicknameValidation() {
        // Given
        BlogReadResponse response = new BlogReadResponse(
                1L,
                "blogName",
                "blogDescription",
                "email@email.com",
                null
        );

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations =
                validator.validate(response);

        // Then
        assertEquals(1, violations.size(),
                "Nickname이 존재하지 않습니다.");
    }

    @Test
    void allBlankValidation() {
        // Given
        BlogReadResponse response = new BlogReadResponse();

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations =
                validator.validate(response);

        // Then
        assertEquals(5, violations.size(),
                "아무 내용도 존재하지 않습니다.");
    }
}
