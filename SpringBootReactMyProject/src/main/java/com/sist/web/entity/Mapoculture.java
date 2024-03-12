package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mapoculture {
	@Id
	private int cno;
	private String name, address, phone, homepage, tag, poster, content;
}
