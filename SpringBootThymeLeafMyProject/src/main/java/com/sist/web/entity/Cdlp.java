package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
/*
	NO int 
	POSTER text 
	SUBJECT text 
	CONTENT text 
	IMAGE text 
	SCORE int 
	ARTIST text 
	PUBLISHER text 
	REGDATE text 
	PRICE int 
	SALEPRICE int 
	DISCOUNT int 
	INVENTORY int 
	GENRE text 
	SUB_GENRE text
 */
@Entity
@Data
public class Cdlp {
	@Id
	private int no;
	private String poster, subject, content, image, artist, publisher;
	private String regdate, genre, sub_genre;
	private int price, saleprice, discount, inventory;
}
