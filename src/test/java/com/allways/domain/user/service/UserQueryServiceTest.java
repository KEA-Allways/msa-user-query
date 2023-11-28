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
    void ReadUserByUserSeqTest() {
        // Given
        User user = UserFactory.createUser();

        when(userRepository.findUserByUserSeq(user.getUserSeq())).thenReturn(Optional.of(user));
        when(blogRepository.findBlogByUserSeq(user.getUserSeq())).thenReturn(Optional.empty()); // Assuming no blog found for the userSeq

        // When
        // UserReadResponse 는 userSeq, nickname, blogName 만을 가지고 있다.
        UserReadResponse userReadResponse = userQueryService.readUserBySeq(user.getUserSeq());

        // Then
        assertNotNull(userReadResponse);
        assertEquals(user.getUserSeq(), userReadResponse.getUserSeq());
        assertEquals(user.getNickname(), userReadResponse.getNickname());
    }

    @Test
    void testReadUserById() {
        // Given
        User user = UserFactory.createUser();

        when(userRepository.findUserByUserId(user.getUserId())).thenReturn(Optional.of(user));
        when(blogRepository.findBlogByUserSeq(user.getUserSeq())).thenReturn(Optional.empty()); // Assuming no blog found for the userSeq

        // When
        UserReadResponse userReadResponse = userQueryService.readUserById(user.getUserId());

        // Then
        assertNotNull(userReadResponse);
        assertEquals(user.getUserSeq(), userReadResponse.getUserSeq());
        assertEquals(user.getNickname(), userReadResponse.getNickname());
    }
}
