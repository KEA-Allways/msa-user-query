package com.allways.common.factory.blog;

import com.allways.domain.blog.dto.BlogReadResponse;

public class BlogReadResponseFactory {
    public static BlogReadResponse createBlogReadResponse() {
        return new BlogReadResponse(20L, "blogNameqwer",
                "blogDescriptionqwer", "emailqwer@email.com",
                "nicknameqwer");
    }

    public static BlogReadResponse createBlogReadResponse(Long blogSeq, String blogName,
                                                          String blogDescription, String email,
                                                          String nickname) {
        return new BlogReadResponse(blogSeq, blogName, blogDescription, email, nickname);
    }
}
