package com.poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.poly.dao.UserDAOImpl;
import com.poly.entity.User;

@WebServlet("/forgotPass")
public class forgotPassServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/html/forgetPass.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		
		UserDAOImpl userDAO = new UserDAOImpl();
		User user = userDAO.findByIdOrEmail(username);

		if (user != null && user.getEmail().equals(email)) {
			String subject = "Khôi phục mật khẩu";
			String body = "Chào " + user.getFullname() + ",\n\nMật khẩu của bạn là: " + user.getPassword();

			boolean emailSent = userDAO.sendEmailToUser(user.getId(), subject, body);

			HttpSession session = request.getSession();
			if (emailSent) {
				session.setAttribute("message", "Một email khôi phục mật khẩu đã được gửi đến địa chỉ email của bạn.");
			} else {
				session.setAttribute("message", "Gửi email không thành công. Vui lòng thử lại sau.");
			}
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("message", "Không tìm thấy người dùng với username và email đã cung cấp.");
		}
		request.getRequestDispatcher("/html/forgetPass.jsp").forward(request, response);
	}
}
