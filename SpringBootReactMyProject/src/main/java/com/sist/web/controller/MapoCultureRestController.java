package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.MapoCultureDAO;
import com.sist.web.dao.MapoNatureDAO;
import com.sist.web.entity.Mapoculture;
import com.sist.web.entity.Maponature;

@RestController
@CrossOrigin(origins = "*")
public class MapoCultureRestController {
	@Autowired
	private MapoCultureDAO dao;
	
	@GetMapping("/culture/list_react")
	public List<Mapoculture> cultureListData(int page)
	{
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<Mapoculture> list=dao.cultureListData(start);
		
		/*Map map=new HashMap();
		int totalpage=dao.cultureListTotalPage();
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
	@GetMapping("culture/culture_totalpage_react")
	public String culture_totalpage()
	{
		int total=dao.cultureListTotalPage();
		return String.valueOf(total);
	}
	
	@GetMapping("/culture/detail_react")
	public Mapoculture cultureDetailData(int cno)
	{
		Mapoculture vo=dao.findByCno(cno);
		return vo;
	}
}	
