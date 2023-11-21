package com.allways.domain.blog.repository;

import com.allways.domain.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("select b from Blog b left join User u on b.user.userSeq = u.userSeq where b.user.userSeq = :userSeq")
    Optional<Blog> findBlogByUserSeq(@Param("userSeq") Long userSeq);

}
