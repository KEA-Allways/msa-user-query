package com.allways.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserByPostRequest {
	private Long postSeq;
	private Long userSeq;
}
