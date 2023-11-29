package com.allways.domain.user.controller;

import com.allways.common.response.Response;
import com.allways.domain.user.service.UserQueryService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserQueryController {

    private final UserQueryService userQueryService;

    // 요청자의 정보 조회
    @GetMapping("/api/user")
    @ResponseStatus(HttpStatus.OK)
    public Response readUser(@RequestHeader(value = "userSeq") Long userSeq) {;
        return Response.success(userQueryService.readUserBySeq(userSeq));
    }

    // userSeq 아니라 userId로 하는게 더 맞는 방식이 아닐까 고민 중 - 류창민
    @GetMapping("/api/user/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readSpecificUser(@PathVariable Long userSeq) {
        return Response.success(userQueryService.readUserBySeq(userSeq));
    }
}
