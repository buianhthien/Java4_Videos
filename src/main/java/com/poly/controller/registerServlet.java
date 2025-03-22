package com.poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.poly.dao.UserDAO;
import com.poly.dao.UserDAOImpl;
import com.poly.entity.User;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
	private UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		userDAO = new UserDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/html/Registred.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");

		if (username == null || username.isEmpty() || password == null || password.isEmpty() || fullname == null
				|| fullname.isEmpty() || email == null || email.isEmpty()) {
			request.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin.");
			request.getRequestDispatcher("/html/Registred.jsp").forward(request, response);
			return;
		}

		if (userDAO.findByIdOrEmail(username) != null) {
			request.setAttribute("errorMessage", "Tên người dùng đã tồn tại. Vui lòng chọn tên khác.");
			request.getRequestDispatcher("/html/Registred.jsp").forward(request, response);
			return;
		}

		if (userDAO.findByIdOrEmail(email) != null) {
			request.setAttribute("errorMessage", "Email đã được sử dụng. Vui lòng chọn email khác.");
			request.getRequestDispatcher("/html/Registred.jsp").forward(request, response);
			return;
		}

		User user = new User();
		user.setId(username);
		user.setPassword(password);
		user.setFullname(fullname);
		user.setEmail(email);
		user.setAdmin(false);

		try {
			userDAO.create(user);
			String subject = "Chào mừng bạn đến với hệ thống!";
			String body = "Chào " + fullname + ",\n\nCảm ơn bạn đã đăng ký tài khoản.";
			boolean emailSent = EmailUntil.sendEmail(email, subject, body);

			if (emailSent) {
				request.setAttribute("successMessage", "Đăng ký thành công! Email xác nhận đã được gửi.");
			} else {
				request.setAttribute("warningMessage", "Đăng ký thành công, nhưng không thể gửi email xác nhận.");
			}

			request.getRequestDispatcher("/html/Registred.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Đăng ký thất bại. Vui lòng thử lại.");
			request.getRequestDispatcher("/html/Registred.jsp").forward(request, response);
		}
	}
}