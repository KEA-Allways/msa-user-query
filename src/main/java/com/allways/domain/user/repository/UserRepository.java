package com.allways.domain.user.repository;

import java.util.List;
import java.util.Optional;

import com.allways.domain.user.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.userSeq = :userSeq")
    Optional<User> findUserByUserSeq(@Param("userSeq") Long userSeq);

    @Query("select u from User u where u.userId = :userId")
    Optional<User> findUserByUserId(@Param("userId") String userId);

    @Query("SELECT U FROM User U WHERE U.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);
    //feign
    List<User> findUserByUserSeqIn(List<Long> userSeqList);
}
