package com.allways.domain.user.service;

import com.allways.domain.blog.entity.Blog;
import com.allways.domain.blog.repository.BlogRepository;
import com.allways.domain.user.entity.User;
import com.allways.domain.user.dto.UserReadResponse;
import com.allways.domain.user.exception.UserNotFoundException;
import com.allways.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    // read By Seq 이던 Id 이던 해당 유저가 blog 를 가지고 있지 않을 경우 toDto 값으로 blog 에 null 을 담아 보낸다.
    public UserReadResponse readUserBySeq(Long userSeq) {
        User user = userRepository.findUserByUserSeq(userSeq).orElseThrow(UserNotFoundException::new);
        Blog blog = blogRepository.findBlogByUserSeq(userSeq).orElse(null);

        return UserReadResponse.toDto(user, blog);
    }

    public UserReadResponse readUserById(String userId) {
        User user = userRepository.findUserByUserId(userId).orElseThrow(UserNotFoundException::new);
        Blog blog = blogRepository.findBlogByUserSeq(user.getUserSeq()).orElse(null);

        return UserReadResponse.toDto(user, blog);
    }

}
