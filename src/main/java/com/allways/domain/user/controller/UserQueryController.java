package com.allways.domain.user.controller;

import com.allways.common.response.Response;
import com.allways.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserQueryController {

    private final UserService userService;

    // 요청자의 정보 조회
    @GetMapping("/api/user")
    @ResponseStatus(HttpStatus.OK)
    public Response readUser(@RequestHeader(value = "userSeq") Long userSeq) {;
        return Response.success(userService.readUserBySeq(userSeq));
    }

    // 특정 유저의 아이디로 정보 조회
    @GetMapping("/api/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response readSpecificUser(@PathVariable String userId) {
        return Response.success(userService.readUserById(userId));
    }
}
