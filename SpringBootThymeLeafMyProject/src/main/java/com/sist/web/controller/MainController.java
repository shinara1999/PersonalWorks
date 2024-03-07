package com.sist.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.dao.CDLPDAO;
import com.sist.web.entity.Cdlp;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	
	@Autowired
	private CDLPDAO cDao;
	
	@GetMapping("/")
	public String main_page(Model model, HttpServletRequest request)
	{
		Cookie[] cookies=request.getCookies();
		List<Cdlp> cList=new ArrayList<Cdlp>();
		int k=0;
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("CDLP"))
				{
					if(k>7)break;
					String no=cookies[i].getValue();
					Cdlp r=cDao.findByNo(Integer.parseInt(no));
					cList.add(r);
					k++;
				}
			}
		}
		model.addAttribute("cList", cList);
		
		model.addAttribute("main_html", "main/home");
		return "main";
	}
}
