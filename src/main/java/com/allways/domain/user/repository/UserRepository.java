package com.allways.domain.user.repository;

import com.allways.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u left join Blog b on u.userSeq = b.user.userSeq where u.userSeq = :userSeq")
    User findUserByUserSeq(@Param("userSeq") Long userSeq);
}
