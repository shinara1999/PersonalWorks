package com.sist.web.entity;
/*
	NNO int 
	NAME text 
	ADDRESS text 
	PHONE text 
	HOMEPAGE text 
	TAG text 
	POSTER text 
	CONTENT text
 */

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Maponature {
	@Id
	private int nno;
	private String name, address, phone, homepage, tag, poster, content;
}
