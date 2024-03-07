package com.sist.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="thmember")
@Getter
@Setter
@NoArgsConstructor
public class Member {
	
	@Id
	@Column(name="id", unique = true)
	private String id;
	private String pwd;
	private String name;
}
