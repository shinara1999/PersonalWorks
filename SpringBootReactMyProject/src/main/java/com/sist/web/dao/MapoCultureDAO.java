package com.sist.web.dao;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.*;
public interface MapoCultureDAO extends JpaRepository<Mapoculture, Integer>{
	@Query(value = "SELECT * FROM mapoculture "
			+"ORDER BY cno DESC LIMIT :start, 9", nativeQuery = true)
	public List<Mapoculture> cultureListData(@Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/9.0) FROM mapoculture"
			, nativeQuery = true)
	public int cultureListTotalPage();
	
	public Mapoculture findByCno(int cno);
}
