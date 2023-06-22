package com.newlecture.web.controller.admin.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController {
	@RequestMapping("list")
	public String list() {
		return "admin.board.notice.list";
		
	}
	@Autowired
	private ServletContext ctx;
	
	@GetMapping("reg")
	public String reg() {
		return "admin.board.notice.reg";
	}
	
	
	@PostMapping("reg")
	public String reg(String title, String content, String category,String[] foods, MultipartFile[] files) {
		
		for(MultipartFile file : files) {
			long size = file.getSize();
			String fileName = file.getOriginalFilename();
			System.out.printf("filename:%s, fileSize:%d\n",fileName,size);
			
			String webPath="/static/upload";
			String realPath=ctx.getRealPath(webPath);
			System.out.printf("realPath : %s\n", realPath);
			
			
			File savePath = new File(realPath);
			if(!savePath.exists())
				savePath.mkdirs();
			
			realPath += File.separator + fileName;
			File saveFile = new File(realPath);
			try {
				file.transferTo(saveFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		for(String food: foods) {
			System.out.println(food);
		}
		//return String.format("title:%s<br>content:%s<br>category:%s",title, content,category);
		//return "reg";
		return "admin.board.notice.reg";
		
	}
	@RequestMapping("edit")
	public String edit() {
		return "admin.board.notice.edit";
		
	}
	
	@RequestMapping("del")
	public String del() {
		return "";
		
	}
}
