package com.allways.domain.user.controller;

import com.allways.common.response.Response;
import com.allways.domain.user.domain.UserReadRequest;
import com.allways.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //로그인한 유저의 정보 조회
    @GetMapping("/api/users")
    @ResponseStatus(HttpStatus.OK)
    public Response readUser(Long userSeq){
        return Response.success(userService.readUser(userSeq));
    }

    //특정 유저의 정보 조회
    @GetMapping("/api/users/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readUserBlog(@PathVariable Long userSeq){
        return Response.success(userService.readUser(userSeq));
    }


}
