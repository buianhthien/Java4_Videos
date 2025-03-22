package com.poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.poly.dao.UserDAO;
import com.poly.dao.UserDAOImpl;
import com.poly.entity.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		this.userDAO = new UserDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/html/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idOrEmail = request.getParameter("idOrEmail");
		String password = request.getParameter("password");

		User user = userDAO.findByIdOrEmail(idOrEmail);

		if (user != null && user.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/indexServlet");
		} else {
			request.setAttribute("errorMessage", "Sai tài khoản hoặc mật khẩu. Vui lòng đăng nhập lại.");
			request.getRequestDispatcher("/html/login.jsp").forward(request, response);
		}
	}
}