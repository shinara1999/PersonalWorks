package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Showinfodetail {
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
