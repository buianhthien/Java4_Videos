package com.poly.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.poly.dao.VideoDAO;
import com.poly.dao.VideoDAOImpl;
import com.poly.entity.Video;

@WebServlet("/indexServlet")
public class indexServlet extends HttpServlet {

	private VideoDAO video = new VideoDAOImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Video> videoList = video.findAll();

		ServletContext application = req.getServletContext();

		application.setAttribute("videolist", videoList);

		req.getRequestDispatcher("/html/index.jsp").forward(req, resp);
		
	}
}
