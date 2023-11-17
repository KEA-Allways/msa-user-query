package com.allways.common.feign.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserByPostFeignRequest {
	private Long postSeq;
	private Long userSeq;
}
