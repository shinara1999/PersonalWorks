package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
@Controller
public class ShowController {

	@Autowired
	private ShowDAO dao;
	
	@Autowired
	private ShowDetailDAO sDao;
	
	// 댓글용
	@Autowired
	private ReplyDAO rDao;
	
	@GetMapping("/show/main")
	public String show_main(String page, Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize;
		List<Showinfo> list=dao.showListData(start);
		int count=dao.showRowCount();
		int totalpage=(int)(Math.ceil(count/20.0));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("count", count);
		model.addAttribute("list", list);
		
		model.addAttribute("main_html", "show/main");
		return "main";
	}
	
	@GetMapping("/show/detail")
	public String show_detail(int sno, Model model)
	{
		// 댓글용
		List<Reply2> list=rDao.replyListData(sno);
		
		Showinfo svo=sDao.findBySno(sno);
		model.addAttribute("svo", svo);
		
		// 댓글용
		model.addAttribute("list", list);
				
		model.addAttribute("main_html", "show/detail");
		return "main";
	}
	
	@RequestMapping("/show/find")
	public String show_find(String page, String saddress, Model model)
	{
		if(saddress==null)
			saddress="강남";
		
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize;
		List<Showinfo> list=dao.showFindData(start, saddress);
		int totalpage=dao.showFindTotalPage(saddress);
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		model.addAttribute("saddress", saddress);
		model.addAttribute("main_html", "show/find");
		return "main";
	}
}
