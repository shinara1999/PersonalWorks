package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.entity.*;

import jakarta.servlet.http.HttpSession;

import com.sist.web.dao.*;
import java.util.*;

@Controller
public class ReplyController {
	@Autowired
	private ReplyDAO dao;
	
	@PostMapping("/reply/insert")
	public String replyInsert(Reply2 vo, HttpSession session, RedirectAttributes ra)
	{
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		vo.setId(id);
		vo.setName(name);
		dao.save(vo); // insert
		ra.addAttribute("sno", vo.getSno());
		return "redirect:/show/detail";
	}
	
	@GetMapping("/reply/delete")
	public String replyDelete(int sno, int no, RedirectAttributes ra)
	{
		Reply2 vo=dao.findByNo(no);
		dao.delete(vo);
		ra.addAttribute("sno", sno);
		return "redirect:/show/detail";
	}
	
	@PostMapping("/reply/update")
	public String replyUpdate(Reply2 vo, RedirectAttributes ra)
	{
		dao.save(vo);
		ra.addAttribute("sno", vo.getSno());
		return "redirect:/show/detail";
	}
}












