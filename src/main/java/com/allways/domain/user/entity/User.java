package com.allways.domain.user.entity;

import javax.persistence.*;

import com.allways.common.EntityDate;

import com.allways.domain.blog.entity.Blog;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
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

	@Column
	private String profileImgSeq;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Blog blog;
}
