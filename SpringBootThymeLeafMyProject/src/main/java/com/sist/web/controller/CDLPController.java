package com.sist.web.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CDLPController {

	@Autowired
	private CDLPDAO dao;
	
	@GetMapping("/CDLP/main")
	public String CDLP_main(String page, Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize;
		List<Cdlp> list=dao.CDLPListData(start);
		int count=dao.CDLPRowCount();
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
		
		model.addAttribute("main_html", "CDLP/main");
		return "main";
	}
	
	// 쿠키
	@GetMapping("/CDLP/before_detail")
	public String CDLP_detail(int no, RedirectAttributes ra, HttpServletResponse response)
	{
		Cookie cookie=new Cookie("CDLP"+no, String.valueOf(no));
		// 쿠키는 문자열만 저장 가능하다.
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		
		ra.addAttribute("no", no);
		return "redirect:../CDLP/detail";
	}
	
	@GetMapping("/CDLP/detail")
	public String CDLP_detail(int no, Model model)
	{
		
		Cdlp vo=dao.findByNo(no);
		model.addAttribute("vo", vo);
		model.addAttribute("main_html", "CDLP/detail");
		return "main";
	}
}
