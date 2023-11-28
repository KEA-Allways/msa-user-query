package com.allways.domain.user.controller;

import com.allways.common.factory.user.UserReadResponseFactory;
import com.allways.domain.user.dto.UserReadResponse;
import com.allways.domain.user.service.UserQueryService;

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

    @Mock private UserQueryService userQueryService;
    @InjectMocks private UserQueryController userQueryController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userQueryController).build();
    }

    @Test
    void ReadUserTest() throws Exception {
        // Given
        UserReadResponse userReadResponse = UserReadResponseFactory.createUserReadResponse();
        when(userQueryService.readUserBySeq(userReadResponse.getUserSeq())).thenReturn(userReadResponse);

        // When/Then
        mockMvc.perform(get("/api/user")
                        .header("userSeq", String.valueOf(userReadResponse.getUserSeq())))
                .andExpect(status().isOk());
        // You can add more assertions here based on your requirements
    }

    @Test
    void ReadSpecificUserTest() throws Exception {
        // Given
        UserReadResponse userReadResponse = UserReadResponseFactory.createUserReadResponse();
        when(userQueryService.readUserBySeq(userReadResponse.getUserSeq())).thenReturn(userReadResponse);
        when(userQueryService.readUserBySeq(userReadResponse.getUserSeq())).thenReturn(userReadResponse);

        // When/Then
        mockMvc.perform(get("/api/user/{userSeq}", userReadResponse.getUserSeq()))
                .andExpect(status().isOk());
        // You can add more assertions here based on your requirements
    }
}
