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

    @GetMapping("/api/users")
    @ResponseStatus(HttpStatus.OK)
    public Response readUser(@Valid UserReadRequest req){
        return Response.success(userService.readUser(req));
    }

    @GetMapping("/api/users/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readUserBlog(@PathVariable Long userSeq){
        UserReadRequest req = new UserReadRequest();
        req.setUserSeq(userSeq);
        return Response.success(userService.readUser(req));
    }

    @GetMapping("/api/users/detail")
    @ResponseStatus(HttpStatus.OK)
    public Response readUserDetail(){

        return Response.success();
    }


}
