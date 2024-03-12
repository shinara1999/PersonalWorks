package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mapohotel {
	@Id
	private int hno;
	private String name, address, phone, homepage, tag, poster;
}
