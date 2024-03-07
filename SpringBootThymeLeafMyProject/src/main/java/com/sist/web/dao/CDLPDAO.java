package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.Cdlp;
import com.sist.web.entity.Showinfo;

@Repository
public interface CDLPDAO extends JpaRepository<Cdlp, Integer>{
	
	@Query(value = "SELECT no, poster, subject, content, image, artist, publisher, regdate, genre, sub_genre, "
			+"price, saleprice, discount, inventory "
			+"FROM cdlp ORDER BY no ASC "
			+"LIMIT :start, 20", nativeQuery = true)
	public List<Cdlp> CDLPListData(@Param("start") int start);
	
	@Query(value = "SELECT COUNT(*) FROM cdlp", nativeQuery = true)
	public int CDLPRowCount();
	
	// 상세보기
	public Cdlp findByNo(int no);
}
