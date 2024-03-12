package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.Board;
import java.util.*;
import com.sist.web.entity.*;

public interface BoardDAO extends JpaRepository<Board, Integer>{
	@Query(value="SELECT no, subject, name, regdate, hit "
			+"FROM board ORDER BY no DESC "
			+"LIMIT :start, 10", nativeQuery = true)
	public List<BoardVO> boardListData(@Param("start") int start);
	
	@Query Board findByNo(int no);
}
