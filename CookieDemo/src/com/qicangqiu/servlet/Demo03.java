package com.qicangqiu.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qicangqiu.util.CookieUtil;

public class Demo03 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("username");
		String password = request.getParameter("userpassword");
		if ("admin".equals(userName) && "123".equals(password)) {
			Cookie[] cookies = request.getCookies();
			Cookie cookie = CookieUtil.findCookie(cookies, "last");
			if (cookie == null) {
				Cookie c = new Cookie("last", System.currentTimeMillis() + "");
				c.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(c);
				response.getWriter().write(userName + "成功登陆");
			} else {
				long lastVisitedTime = Long.parseLong(cookie.getValue());
				response.getWriter().write(userName + "成功登陆，上次成功登陆时间是：" + new Date(lastVisitedTime));
				cookie.setValue(System.currentTimeMillis() + "");
				response.addCookie(cookie);
			}
		} else {
			response.getWriter().write("失败");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
