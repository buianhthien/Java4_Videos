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

@WebServlet("/changePass")
public class changePassServlet extends HttpServlet {
	private UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		this.userDAO = new UserDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/html/ChangePass.jsp").forward(request, response);
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
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
			request.setAttribute("errorMessage", "Mật khẩu mới và xác nhận mật khẩu không trùng khớp.");
			request.getRequestDispatcher("/html/ChangePass.jsp").forward(request, response);
			return;
		}

		if (currentPassword == null || loginUser.getPassword() == null
				|| !loginUser.getPassword().equals(currentPassword)) {
			request.setAttribute("errorMessage", "Mật khẩu hiện tại không đúng.");
			request.getRequestDispatcher("/html/ChangePass.jsp").forward(request, response);
			return;
		}

		try {
			loginUser.setPassword(newPassword);
			userDAO.updatePassword(loginUser.getId(), newPassword);

			request.setAttribute("successMessage", "Mật khẩu đã được thay đổi thành công.");
			request.getRequestDispatcher("/html/ChangePass.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Có lỗi xảy ra khi thay đổi mật khẩu. Vui lòng thử lại.");
			request.getRequestDispatcher("/html/ChangePass.jsp").forward(request, response);
		}
	}
}