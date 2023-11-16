package com.allways.domain.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allways.domain.user.dto.UserByPostRequest;
import com.allways.domain.user.dto.UserByPostResponse;
import com.allways.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserFeignController {

	private final UserService userService;

	@PostMapping("/api/users/feign")
	@ResponseStatus(HttpStatus.OK)
	public List<UserByPostResponse> queryUser(@RequestBody List<UserByPostRequest> userByPostRequest){

		List<UserByPostResponse> userList = userService.queryUser(userByPostRequest);
		// List<UserFeignResponse> userFeignResponseList = new ArrayList<>();
		return userList;
	}
}
