package com.example.wxgzh.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Captcha {
private static final String CHARS ="23456789abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";
	
	public static String randomCode(int length) {
		char[] result = new char[length];
		for(int i=result.length;i-->0;) {
			result[i] = CHARS.charAt((int)(Math.random()*CHARS.length()));
		}
		return new String(result);
	} 
	public static BufferedImage generate(String code,int width,int height) {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = img.getGraphics();
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, width, height);
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("黑体",Font.BOLD, 30));
		graphics.drawString(code, 28, 28);
		graphics.setColor(Color.BLUE);
		graphics.drawArc(0, 0, width, height, 150, 220);
		return img;
	}
}
