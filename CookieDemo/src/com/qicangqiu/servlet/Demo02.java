package com.qicangqiu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Demo02 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				String cookieName = c.getName();
				String cookieValue = c.getValue();
				System.out.println(cookieName + " = " + cookieValue);
			}
		}
		Cookie cookie = new Cookie("name", "toby");
		cookie.setMaxAge(60 * 60 * 24 * 7);
		response.addCookie(cookie);
		Cookie cookie2 = new Cookie("age", "26");
		cookie2.setMaxAge(60 * 60 * 24 * 7);
		response.addCookie(cookie2);
		response.getWriter().write("请求成功!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
