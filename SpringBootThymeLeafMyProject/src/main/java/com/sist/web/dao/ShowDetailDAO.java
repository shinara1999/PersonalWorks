package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.Showinfo;
import com.sist.web.entity.Showinfodetail;
@Repository
public interface ShowDetailDAO extends JpaRepository<Showinfo, Integer>{
	// 상세보기
	public Showinfo findBySno(int sno);
}
