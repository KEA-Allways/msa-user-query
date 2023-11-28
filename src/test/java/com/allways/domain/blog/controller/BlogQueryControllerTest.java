package com.allways.domain.blog.controller;

import com.allways.common.factory.blog.BlogReadResponseFactory;
import com.allways.common.response.Response;
import com.allways.common.response.Success;
import com.allways.domain.blog.dto.BlogReadResponse;
import com.allways.domain.blog.service.BlogQueryService;
import com.allways.domain.blog.exception.BlogNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogQueryControllerTest {
    @Mock private BlogQueryService blogQueryService;
    @InjectMocks private BlogQueryController blogQueryController;

    @Test
    void readBlogTest() {
        // 주어진 상황 (Given)
        BlogReadResponse expectedResponse = BlogReadResponseFactory.createBlogReadResponse();
        when(blogQueryService.readBlog(expectedResponse.getBlogSeq())).thenReturn(expectedResponse);

        // 실행 (When)
        Response result = blogQueryController.readBlog(expectedResponse.getBlogSeq());

        // 결과 확인 (Then)
        assertNotNull(result);
        assertTrue(result.isSuccess());
        assertEquals(0, result.getCode());

        // Result 객체가 null 아닌지 확인
        assertNotNull(result.getResult());

        // 성공 시 Result 객체에서 데이터 가져오기
        BlogReadResponse responseData = ((Success<BlogReadResponse>) result.getResult()).getData();
        assertEquals(expectedResponse, responseData);

        // BlogService 의 readBlog 메서드가 한 번 호출되었는지 확인
        verify(blogQueryService, times(1)).readBlog(expectedResponse.getBlogSeq());
    }


    @Test
    void readBlog_BlogNotFoundException() {
        // 주어진 상황 (Given)
        Long userSeq = 1L;
        when(blogQueryService.readBlog(userSeq)).thenThrow(BlogNotFoundException.class);

        // 실행 및 예외 확인 (When, Then)
        assertThrows(BlogNotFoundException.class, () -> blogQueryController.readBlog(userSeq));

        // BlogService 의 readBlog 메서드가 한 번 호출되었는지 확인
        verify(blogQueryService, times(1)).readBlog(userSeq);
    }
}
