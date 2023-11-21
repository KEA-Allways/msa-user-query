package com.allways.domain.user.repository;

import java.util.List;

import com.allways.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u left join Blog b on u.userSeq = b.user.userSeq where u.userSeq = :userSeq")
    Optional<User> findUserByUserSeq(@Param("userSeq") Long userSeq);

    @Query("select u from User u left join Blog b on u.userSeq = b.user.userSeq where u.userId = :userId")
    Optional<User> findUserByUserId(@Param("userId") String userId);
    //feign
    List<User> findUserByUserSeqIn(List<Long> userSeqList);
    
}
