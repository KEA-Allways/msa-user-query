package com.allways.common.feign.user;

import java.util.List;

import com.allways.common.feign.user.UserFeignResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.allways.common.feign.user.UserByPostFeignRequest;
import com.allways.common.feign.user.UserByPostFeignResponse;
import com.allways.domain.user.service.UserService;

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
	public List<UserByPostFeignResponse> queryAllUser(@RequestBody List<UserByPostFeignRequest> userByPostFeignRequest){

		List<UserByPostFeignResponse> userList = userFeignService.queryAllUser(userByPostFeignRequest);
		// List<UserFeignResponse> userFeignResponseList = new ArrayList<>();
		return userList;
	}
}
