package com.sist.web.entity;
/*
 	no int AI PK 
	name varchar(51) 
	subject varchar(1000) 
	content text 
	pwd varchar(10) 
	regdate datetime 
	hit int
 */


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board {
	
	@Id
	private int no;
	private String name;
	private String subject;
	private String content;
	
	@Column(insertable = true, updatable = false)
	private String pwd;
	
	@Column(insertable = true, updatable = false)
	private String regdate;
	
	// @Column(insertable = true, updatable = false) 이거지워야함!!!!!!!!!!!!!!
	private int hit;
	
	@PrePersist
	public void regdate() {
		this.regdate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	}
}
