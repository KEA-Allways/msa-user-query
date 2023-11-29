package com.allways.domain.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long blogSeq;

	@Column
	private String blogDescription;

	@Column
	private String blogName;

	@Column
	private Long userSeq;

	public Blog(String blogName, String blogDescription, Long userSeq) {
		this.blogName = blogName;
		this.blogDescription = blogDescription;
		this.userSeq = userSeq;
	}
}
