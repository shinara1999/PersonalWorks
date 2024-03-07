package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.*;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;
import jakarta.servlet.http.HttpSession;

import com.sist.web.dao.*;
@RestController
public class MemberController {
	@Autowired
	private MemberDAO dao;
	
	@PostMapping("/member/login")
	public String memberLogin(String id, String pwd, HttpSession session)
	{
		String result="";
		int count=dao.memberIdCount(id);
		if(count==0)
		{
			result="NOID";
		}
		else
		{
			Member vo=dao.memberInfoData(id);
			if(pwd.equals(vo.getPwd()))
			{
				result="OK";
				session.setAttribute("id", vo.getId());
				session.setAttribute("name", vo.getName());
			}
			else
			{
				result="NOPWD";
			}
		}
		return result;
	}
	
	@GetMapping("/member/logout")
	public String memberLogout(HttpSession session)
	{
		String result="";
		try
		{
			result="yes";
			session.invalidate();
			
		}catch(Exception e)
		{
			result="no";
		}
		return result;
	}
}






