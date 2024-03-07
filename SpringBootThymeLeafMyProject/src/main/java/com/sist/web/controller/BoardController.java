package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.web.dao.BoardDAO;
import com.sist.web.entity.Board;
import com.sist.web.entity.BoardVO;

@Controller
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	@GetMapping("/board/list")
	public String boardList(String page, Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-rowSize;
		List<BoardVO> list=dao.boardListData(start);
		int count=(int)dao.count();
		int totalpage=(int)(Math.ceil(count/10.0));
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		model.addAttribute("main_html", "board/list");
		return "main";
	}
	
	@GetMapping("/board/insert")
	public String boardInsert(Model model)
	{
		model.addAttribute("main_html", "board/insert");
		return "main";
	}
	@PostMapping("/board/insert_ok")
	public String boardInsertOk(Board vo)
	{
		dao.save(vo);
		return "redirect:/board/list";
	}
	
   @GetMapping("/board/detail")
   public String board_detail(int no,Model model)
   {
	   Board vo=dao.findByNo(no);
	   vo.setHit(vo.getHit()+1);
	   dao.save(vo);
	   // 조회수 증가 
	   vo=dao.findByNo(no);
	   model.addAttribute("vo", vo);
	   model.addAttribute("main_html", "board/detail");
	   return "main";
   }
   
   @GetMapping("/board/update")
   public String board_update(int no,Model model)
   {
	   Board vo=dao.findByNo(no);
	   model.addAttribute("vo", vo);
	   model.addAttribute("main_html", "board/update");
	   return "main";
   }
   @PostMapping("/board/update_ok")
   @ResponseBody
   public String board_update_ok(Board vo)
   {
	   String result="";
	   Board en=dao.findByNo(vo.getNo());
	   if(en.getPwd().equals(vo.getPwd()))
	   {
		   result="<script>"
				  +"location.href=\"/board/detail?no="+vo.getNo()+"\""
				  +"</script>";
		   
		   dao.save(vo);
	   }
	   else
	   {
		   result="<script>"
				 +"alert(\"비밀번호를 다시 입력하세요.\");"
				 +"history.back();"
				 +"</script>";
	   }
	   return result;
   }
   
   @GetMapping("/board/delete")
   public String board_delete(int no,Model model)
   {
	   model.addAttribute("no", no);
	   model.addAttribute("main_html", "board/delete");
	   return "main";
   }
   @PostMapping("/board/delete_ok")
   @ResponseBody
   public String board_delete_ok(int no,String pwd)
   {
	   String result="";
	   Board vo=dao.findByNo(no);
	   if(pwd.equals(vo.getPwd()))
	   {
		   result="<script>"
				 +"location.href=\"/board/list\""
				 +"</script>";
		   dao.delete(vo);
	   }
	   else
	   {
		      result="<script>"
					 +"alert(\"비밀번호를 다시 입력하세요.\");"
					 +"history.back();"
					 +"</script>";
	   }
	   return result;
   }
}
