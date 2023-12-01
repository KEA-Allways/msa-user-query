package com.allways.domain.user.entity;

import javax.persistence.*;

import com.allways.common.EntityDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
public class User extends EntityDate {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userSeq;

	@Column(nullable = false, unique = true)
	private String userId;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	private String nickname;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String profileImgSeq;

	public User(String userId, String password, String nickname, String email, String profileImgSeq){
		this.userId = userId;
		this.password = password;
		this.nickname = nickname;
		this.email = email;
		this.profileImgSeq = profileImgSeq;
	}
}
