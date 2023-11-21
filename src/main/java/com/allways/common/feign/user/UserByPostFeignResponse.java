package com.allways.common.feign.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserByPostFeignResponse {
	private Long postSeq;
	private Long userSeq;
	private String userId;
	private String nickname;
}
