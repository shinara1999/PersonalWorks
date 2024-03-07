package com.sist.web.dao;


import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;

@Repository
public interface ShowDAO extends JpaRepository<Showinfo, Integer>{
	@Query(value = "SELECT sno, scate, sdetail, sposter, stitle, sdate, sloc, sgrade, shour, sperformer, sseat, stime, sdelivery, sdeposter, sdeloc, saddress, sphone "
			+"FROM ShowInfo ORDER BY sno ASC "
			+"LIMIT :start, 20", nativeQuery = true)
	public List<Showinfo> showListData(@Param("start") int start);
	
	@Query(value = "SELECT COUNT(*) FROM ShowInfo", nativeQuery = true)
	public int showRowCount();
	
	// 검색
	@Query(value = "SELECT sno, scate, sdetail, sposter, stitle, sdate, sloc, sgrade, shour, sperformer, sseat, stime, sdelivery, sdeposter, sdeloc, saddress, sphone "
			+"FROM ShowInfo WHERE saddress LIKE CONCAT('%', :saddress, '%') "
			+"LIMIT :start, 20", nativeQuery = true)
	public List<Showinfo> showFindData(@Param("start") Integer start, @Param("saddress") String saddress);
	
	@Query(value = "SELECT CEIL(COUNT(*)/20.0) FROM ShowInfo "
			+"WHERE saddress LIKE CONCAT('%', :saddress, '%')"
			, nativeQuery = true)
	public int showFindTotalPage(@Param("saddress") String saddress);
	
	
}
