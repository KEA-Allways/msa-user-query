package com.allways.domain.blog.repository;

import com.allways.common.factory.blog.BlogFactory;
import com.allways.domain.blog.entity.Blog;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 데이터베이스 사용
@SpringBootTest
@ActiveProfiles("test")
public class BlogRepositoryTest {

    @Autowired private BlogRepository blogRepository;
    @Autowired private EntityManager entityManager;

    @Test
    @Transactional
    void FindBlogByUserSeqTest() {
        // Given
        Blog blog = BlogFactory.createBlog();
        entityManager.persist(blog);
        entityManager.flush();

        // When
        Optional<Blog> foundBlog = blogRepository.findBlogByUserSeq(blog.getUserSeq());

        // Then
        assertTrue(foundBlog.isPresent());
        assertEquals(blog.getBlogName(), foundBlog.get().getBlogName());
        assertEquals(blog.getBlogDescription(), foundBlog.get().getBlogDescription());
        assertEquals(blog.getUserSeq(), foundBlog.get().getUserSeq());

        blogRepository.deleteById(blog.getBlogSeq()); // test 후 db에 저장한 내용 삭제
    }
}
