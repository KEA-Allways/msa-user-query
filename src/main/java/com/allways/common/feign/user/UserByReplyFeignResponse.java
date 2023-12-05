package com.allways.common.feign.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserByReplyFeignResponse {
	private Long replySeq;
	private Long userSeq;
	private String userId;
	private String nickname;
}
