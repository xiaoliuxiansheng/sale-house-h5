package com.example.wxgzh.picture.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/avatar")
public class PictureController {

	@GetMapping("/*")
	public void avatar(HttpServletRequest req, HttpServletResponse resp)throws Exception{
		String url = req.getRequestURI();
		String avatarPath = url.substring(url.lastIndexOf('/')+1);
		int potIndex = avatarPath.lastIndexOf('.');
		if(potIndex==-1) {
			potIndex=0;
		}
		String fileSufix = avatarPath.substring(potIndex);
		String saveDir = "f:/upload";
		resp.setContentType(req.getServletContext().getMimeType(fileSufix));

		try(ServletOutputStream out = resp.getOutputStream();
			FileInputStream in=new FileInputStream(
					new File(saveDir,avatarPath));
		){
			byte[] buf=new byte[10240];
			int readed=0;
			while((readed=in.read(buf))!=-1) {
				out.write(buf,0,readed);
			}
		}
	}
}