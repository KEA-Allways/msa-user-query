package com.allways.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserByPostResponse {
	private Long postSeq;
	private Long userSeq;
	private String id;
	private String nickname;
}
