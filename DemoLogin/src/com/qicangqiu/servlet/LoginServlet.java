package com.qicangqiu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("userpassword");
		System.out.print(userName + "   " + password);
		PrintWriter pw = response.getWriter();
		if ("admin".equals(userName) && "123".equals(password)) {
//			pw.write("login success...");
			Object obj = getServletContext().getAttribute("count");
			int totalCount = 0;
			if (obj != null) {
				totalCount = (int) obj;
			}
			System.out.println("the number of successful logins: " + totalCount);
			getServletContext().setAttribute("count", ++totalCount);
			response.setStatus(302);
			response.setHeader("Location", "login_success.html");
		} else {
			pw.write("login fail...");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
