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
    void NotBlankValidation() {
        // Given
        BlogReadResponse response = BlogReadResponseFactory.createBlogReadResponse();

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations = validator.validate(response);

        // Then
        assertEquals(0, violations.size());
    }

    @Test
    void BlogSeqBlankValidation() {
        // Given
        BlogReadResponse response = new BlogReadResponse(
                null,
                "blogName",
                "blogDescription",
                "email@email.com",
                "nickname"
        );

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations = validator.validate(response);

        // Then
        assertEquals(1, violations.size());
    }

    @Test
    void BlogNameBlankValidation() {
        // Given
        BlogReadResponse response = new BlogReadResponse(
                1L,
                null,
                "blogDescription",
                "email@email.com",
                "nickname"
        );

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations = validator.validate(response);

        // Then
        assertEquals(1, violations.size());
    }

    @Test
    void BlogDescriptionBlankValidation() {
        // Given
        BlogReadResponse response = new BlogReadResponse(
                1L,
                "blogName",
                null,
                "email@email.com",
                "nickname"
        );

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations = validator.validate(response);

        // Then
        assertEquals(1, violations.size());
    }

    @Test
    void EmailBlankValidation() {
        // Given
        BlogReadResponse response = new BlogReadResponse(
                1L,
                "blogName",
                "blogDescription",
                null,
                "nickname"
        );

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations = validator.validate(response);

        // Then
        assertEquals(1, violations.size()); // Expecting 5 violations for 5 @NotBlank fields
    }

    @Test
    void NicknameBlankValidation() {
        // Given
        BlogReadResponse response = new BlogReadResponse(
                1L,
                "blogName",
                "blogDescription",
                "email@email.com",
                null
        );

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations = validator.validate(response);

        // Then
        assertEquals(1, violations.size()); // Expecting 5 violations for 5 @NotBlank fields
    }

    @Test
    void AllBlankValidation() {
        // Given
        BlogReadResponse response = new BlogReadResponse();

        // When
        Set<ConstraintViolation<BlogReadResponse>> violations = validator.validate(response);

        // Then
        assertEquals(5, violations.size()); // Expecting 5 violations for 5 @NotBlank fields
    }
}
