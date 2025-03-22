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

@WebServlet("/editProfile")
public class editProfileServlet extends HttpServlet {
	private UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		this.userDAO = new UserDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/html/editProfile.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("user");
		if (loginUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");

		if (username == null || username.isEmpty() || fullname == null || fullname.isEmpty() || email == null
				|| email.isEmpty()) {
			request.setAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin.");
			request.getRequestDispatcher("/html/editProfile.jsp").forward(request, response);
			return;
		}

		loginUser.setId(username);
		loginUser.setPassword(password);
		loginUser.setFullname(fullname);
		loginUser.setEmail(email);

		try {
			userDAO.update(loginUser);
			session.setAttribute("user", loginUser);
			request.setAttribute("successMessage", "Thông tin đã được cập nhật thành công.");
			request.getRequestDispatcher("/html/editProfile.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật thông tin. Vui lòng thử lại.");
			request.getRequestDispatcher("/html/editProfile.jsp").forward(request, response);
		}
	}
}