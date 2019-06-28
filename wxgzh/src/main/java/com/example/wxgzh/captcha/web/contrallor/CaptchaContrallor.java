package com.example.wxgzh.captcha.web.contrallor;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.wxgzh.common.util.Captcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api")
public class CaptchaContrallor {
	@GetMapping("/captcha")
	public void captchaService(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String code = Captcha.randomCode(6);
		req.getSession().setAttribute("captcha", code);
		BufferedImage img =  Captcha.generate(code,150, 40);
		resp.setContentType("image/png");
		try(ServletOutputStream out = resp.getOutputStream()) {
			ImageIO.write(img, "png", out);
		}
	}
}
