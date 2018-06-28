package com.qicangqiu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qicangqiu.dao.StuDao;
import com.qicangqiu.dao.UserDao;
import com.qicangqiu.dao.impl.StuDaoImpl;
import com.qicangqiu.dao.impl.UserDaoImpl;
import com.qicangqiu.domain.Student;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
//		System.out.println(userName + "  " + password);
		UserDao dao = new UserDaoImpl();
		boolean isSuccess = dao.login(userName, password);
		if (isSuccess) {
//			response.getWriter().write("登陆成功。");
			StuDao stuDao = new StuDaoImpl();
			List<Student> list = stuDao.findAll();
			request.getSession().setAttribute("list", list);
			response.sendRedirect("stu_list.jsp");
		} else {
			response.getWriter().write("用户名或密码错误！");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
