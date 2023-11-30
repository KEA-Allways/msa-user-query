package com.allways.domain.user.repository;

import com.allways.common.factory.user.UserFactory;
import com.allways.domain.user.entity.User;

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
public class UserRepositoryTest {
    @Autowired private UserRepository userRepository;
    @Autowired private EntityManager entityManager;

    @Test
    @Transactional
    void findUserByUserSeqTest() {
        // Given
        User user = UserFactory.createUser();

        entityManager.persist(user);
        entityManager.flush();

        // When
        Optional<User> foundUser = userRepository.findUserByUserSeq(user.getUserSeq());

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals(user.getUserId(), foundUser.get().getUserId());
        assertEquals(user.getPassword(), foundUser.get().getPassword());
        assertEquals(user.getNickname(), foundUser.get().getNickname());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
        assertEquals(user.getProfileImgSeq(), foundUser.get().getProfileImgSeq());

        // 잊지 말고 테스트 후 데이터 삭제
        userRepository.deleteById(user.getUserSeq());
    }

    @Test
    @Transactional
    void findUserByUserIdTest() {
        // Given
        User user = UserFactory.createUser();

        entityManager.persist(user);
        entityManager.flush();

        // When
        Optional<User> foundUser = userRepository.findUserByUserId(user.getUserId());

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals(user.getUserId(), foundUser.get().getUserId());
        assertEquals(user.getPassword(), foundUser.get().getPassword());
        assertEquals(user.getNickname(), foundUser.get().getNickname());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
        assertEquals(user.getProfileImgSeq(), foundUser.get().getProfileImgSeq());

        // 잊지 말고 테스트 후 데이터 삭제
        userRepository.deleteById(user.getUserSeq());
    }
}
