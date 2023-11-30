package com.allways.domain.user.controller;

import com.allways.common.factory.user.UserReadResponseFactory;
import com.allways.domain.user.dto.UserReadResponse;
import com.allways.domain.user.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserQueryControllerTest {
    @Mock private UserService userService;
    @InjectMocks private UserQueryController userQueryController;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(userQueryController).build();
    }

    @Test
    void readUserTest() throws Exception {
        // Given
        Long userSeq = 1L;
        UserReadResponse userReadResponse = UserReadResponseFactory.createUserReadResponse();

        // When
        when(userService.readUserBySeq(userSeq)).thenReturn(userReadResponse);

        // Then
        mockMvc.perform(get("/api/user")
                        .header("userSeq", String.valueOf(userSeq)))
                .andExpect(status().isOk());
    }

    @Test
    void readSpecificUserTest() throws Exception {
        // Given
        UserReadResponse userReadResponse = UserReadResponseFactory.createUserReadResponse();
        Long userSeq = userReadResponse.getUserSeq();

        // When
        when(userService.readUserBySeq(userSeq)).thenReturn(userReadResponse);

        // Then
        mockMvc.perform(get("/api/user/{userSeq}", userSeq))
                .andExpect(status().isOk());
    }
}
