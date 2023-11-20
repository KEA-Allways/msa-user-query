package com.allways.common.feign.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserFeignController {

	private final UserFeignService userFeignService;

	@GetMapping("/api/users/feign/{userSeq}")
	@ResponseStatus(HttpStatus.OK)
	public UserFeignResponse queryUser(@PathVariable Long userSeq){
		return userFeignService.queryUser(userSeq);
	}

	@PostMapping("/api/users/feign")
	@ResponseStatus(HttpStatus.OK)
	public List<UserByPostFeignResponse> queryUsersByPost(@RequestBody List<UserByPostFeignRequest> userByPostFeignRequest){
		return userFeignService.queryUsersByPost(userByPostFeignRequest);
	}
}
