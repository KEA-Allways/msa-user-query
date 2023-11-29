package com.allways.domain.user.service;

import com.allways.common.factory.user.UserFactory;
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
public class UserQueryServiceTest {
    @Mock private UserRepository userRepository;
    @Mock private BlogRepository blogRepository;
    @InjectMocks private UserQueryService userQueryService;

    @Test
    void readUserByUserSeqTest() {
        // Given
        User user = UserFactory.createUser();
        Long userSeq = user.getUserSeq();

        // When
        when(userRepository.findUserByUserSeq(userSeq)).thenReturn(Optional.of(user));
        when(blogRepository.findBlogByUserSeq(userSeq)).thenReturn(Optional.empty()); // Assuming no blog found for the userSeq

        // UserReadResponse 는 userSeq, nickname, blogName 만을 가지고 있다.
        UserReadResponse userReadResponse = userQueryService.readUserBySeq(userSeq);

        // Then
        assertNotNull(userReadResponse);
        assertEquals(userSeq, userReadResponse.getUserSeq());
        assertEquals(user.getNickname(), userReadResponse.getNickname());
    }

    @Test
    void readUserByIdTest() {
        // Given
        User user = UserFactory.createUser();
        String userId = user.getUserId();
        Long userSeq = user.getUserSeq();

        // When
        when(userRepository.findUserByUserId(userId)).thenReturn(Optional.of(user));
        when(blogRepository.findBlogByUserSeq(userSeq)).thenReturn(Optional.empty());

        UserReadResponse userReadResponse = userQueryService.readUserById(userId);

        // Then
        // userReadResponse의 내용물은 userSeq, nickname, blogName이다.
        assertNotNull(userReadResponse);
        assertEquals(userSeq, userReadResponse.getUserSeq());
        assertEquals(user.getNickname(), userReadResponse.getNickname());
    }
}
