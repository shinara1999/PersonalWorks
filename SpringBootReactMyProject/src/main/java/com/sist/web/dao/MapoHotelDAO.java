package com.sist.web.dao;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sist.web.entity.*;
public interface MapoHotelDAO extends JpaRepository<Mapohotel, Integer>{
	@Query(value = "SELECT * FROM mapohotel "
			+"ORDER BY hno DESC LIMIT :start, 9", nativeQuery = true)
	public List<Mapohotel> hotelListData(@Param("start") int start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/9.0) FROM mapohotel"
			, nativeQuery = true)
	public int hotelListTotalPage();
	
	// 찾기
	@Query(value = "SELECT * FROM mapohotel "
			+"WHERE address LIKE CONCAT('%', :address, '%') "
			+"ORDER BY hno ASC LIMIT :start, 9", nativeQuery = true)
	public List<Mapohotel> hotelFindData(@Param("start") Integer start, @Param("address") String address);
	
	@Query(value = "SELECT CEIL(COUNT(*)/9.0) FROM mapohotel "
			+"WHERE address LIKE CONCAT('%', :address, '%')", nativeQuery = true)
	public int hotelFindTotalPage(@Param("address") String address);
	
	public Mapohotel findByHno(int hno);
}
