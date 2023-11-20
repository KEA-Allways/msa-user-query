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
    // RequestHeader로 받아오는 userSeq는 요청자의 userSeq이기에 api를 통해 호출하는 userSeq와는 다를 수도 있다.
    @GetMapping("/api/users")
    @ResponseStatus(HttpStatus.OK)
    public Response readUser(@RequestHeader(value = "userSeq") Long userSeq) {;
        return Response.success(userService.readUserBySeq(userSeq));
    }

    // 특정 유저의 정보 조회
    // 뒤의 코드를 보니 userSeq로 사용할때랑 userId로 사용할때 사용법을 다르게 하려고 userId로 적어둔 모양임
    // UserId는 String이고 UserSeq는 Long으로 아예 서로 다른 개념
    @GetMapping("/api/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response readSpecificUser(@PathVariable String userId) {
        return Response.success(userService.readUserById(userId));
    }
}
