package com.sist.web.dao;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.*;
public interface MapoNatureDAO extends JpaRepository<Maponature, Integer>{
	@Query(value = "SELECT * FROM maponature "
			+"ORDER BY nno DESC LIMIT :start, 9", nativeQuery = true)
	public List<Maponature> natureListData(@Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/9.0) FROM maponature"
			, nativeQuery = true)
	public int natureListTotalPage();
	
	public Maponature findByNno(int nno);
	
	
}
