package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.MapoHotelDAO;
import com.sist.web.entity.Mapohotel;
import com.sist.web.entity.Maponature;

@RestController
@CrossOrigin(origins = "*")
public class MapoHotelRestController {
	@Autowired
	private MapoHotelDAO dao;
	
	@GetMapping("/hotel/list_react")
	public List<Mapohotel> hotelListData(int page)
	{
		int rowSize=3;
		int start=(rowSize*page)-rowSize;
		List<Mapohotel> list=dao.hotelListData(start);
		
		/*Map map=new HashMap();
		int totalpage=dao.hotelListTotalPage();
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("list", list);
		
		return map;*/
		return list;
	}
	
	// redux 용
	@GetMapping("/hotel/hotel_totalpage_react")
	public String hotel_totalpage()
	{
		int total=dao.hotelListTotalPage();
		return String.valueOf(total);
	}
	
	// redux 용
	@GetMapping("/hotel/find_totalpage_react")
	public String find_totalpage(String address)
	{
		int total=dao.hotelFindTotalPage(address);
		return String.valueOf(total);
	}
	
	// 검색
	@RequestMapping("/hotel/find_react")
	public List<Mapohotel> hotel_find(int page, String address)
	{
		int rowSize=6;
		int start=(rowSize*page)-rowSize;
		List<Mapohotel> list=dao.hotelFindData(start, address);
		/*Map map=new HashMap();
		
		int totalpage=dao.hotelFindTotalPage(address);
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("list", list);
		
		return map;*/
		return list;
	}
	
	@GetMapping("/hotel/detail_react")
	public Mapohotel hotelDetailData(int hno)
	{
		Mapohotel vo=dao.findByHno(hno);
		return vo;
	}
}
