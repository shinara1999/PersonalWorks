package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/*
	SNO int 
	SCATE int 
	SDETAIL int 
	SPOSTER text 
	STITLE text 
	SDATE text 
	SLOC text 
	SGRADE text 
	SHOUR text 
	SPERFORMER text 
	SSEAT text 
	STIME text 
	SDELIVERY text 
	SDEPOSTER text 
	SDELOC text 
	SADDRESS text 
	SPHONE text 
	HIT int 
	LIKECOUNT int
 */
@Entity
@Getter
@Setter
public class Showinfo {
	@Id
	private int sno;
	private int scate;
	private int sdetail;
	private String sposter;
	private String stitle;
	private String sdate;
	private String sloc;
	private String sgrade, shour, sperformer, sseat, stime, sdelivery, sdeposter, sdeloc, saddress, sphone;
	
}
