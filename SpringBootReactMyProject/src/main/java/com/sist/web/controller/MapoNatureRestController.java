package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.MapoNatureDAO;
import com.sist.web.entity.Maponature;

@RestController
@CrossOrigin(origins = "*")
public class MapoNatureRestController {
	@Autowired
	private MapoNatureDAO dao;
	
	@GetMapping("/nature/list_react")
	public List<Maponature> natureListData(int page)
	{
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<Maponature> list=dao.natureListData(start);
		
		/*Map map=new HashMap();
		int totalpage=dao.natureListTotalPage();
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
	@GetMapping("nature/nature_totalpage_react")
	public String food_totalpage()
	{
		int total=dao.natureListTotalPage();
		return String.valueOf(total);
	}
	
	@GetMapping("/nature/detail_react")
	public Maponature natureDetailData(int nno)
	{
		Maponature vo=dao.findByNno(nno);
		return vo;
	}
}
