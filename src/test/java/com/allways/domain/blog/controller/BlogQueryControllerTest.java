package com.allways.domain.blog.controller;

import com.allways.domain.blog.service.BlogService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BlogQueryControllerTest {
    private MockMvc mockMvc;
    @Mock private BlogService blogService;
    @InjectMocks private BlogQueryController blogQueryController;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(blogQueryController).build();
    }

    @Test
    void readBlogTest() throws Exception {
        // Given
        Long userSeq = 1L;

        // When, Then
        mockMvc.perform(get("/api/blog/{userSeq}", userSeq)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(blogService).readBlog(userSeq);
    }
}
