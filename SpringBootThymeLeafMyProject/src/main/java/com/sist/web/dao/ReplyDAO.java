package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.sist.web.entity.Reply2;

@Repository
public interface ReplyDAO extends JpaRepository<Reply2, Integer>{
	
	// 데이터 읽기
	@Query(value = "SELECT * FROM reply2 WHERE sno=:sno "
			+"ORDER BY sno DESC",
			nativeQuery = true)
	public List<Reply2> replyListData(@Param("sno") int sno);
	
	public Reply2 findByNo(int no);
	// insert, update, delete
}
