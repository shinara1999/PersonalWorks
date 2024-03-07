package com.sist.web.entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
public class Reply2 {
	@Id
	private int no;
	
	@Column(insertable = true, updatable = false)
	private int sno;
	
	@Column(insertable = true, updatable = false)
	private String id;
	
	@Column(insertable = true, updatable = false)
	private String name;
	
	private String msg;
	
	@Column(insertable = true, updatable = false)
	private String regdate;
	
	@PrePersist
	public void regdate() {
		this.regdate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
	}
}
