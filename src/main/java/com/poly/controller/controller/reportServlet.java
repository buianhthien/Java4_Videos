package com.poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.poly.dao.FavoriteDAO;
import com.poly.dao.FavoriteDAOImpl;
import com.poly.dao.ShareDAO;
import com.poly.dao.ShareDAOImpl;

@WebServlet({ "/report", "/report/favoriteuser", "/report/shareUser" })
public class reportServlet extends HttpServlet {
	private FavoriteDAO favoriteDAO;
	private ShareDAO shareDAO;

	@Override
	public void init() throws ServletException {
		this.favoriteDAO = new FavoriteDAOImpl();
		this.shareDAO = new ShareDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Object[]> videoLikeStatistics = favoriteDAO.findVideoFavoriteStatistics();
			request.setAttribute("videoLikeStatistics", videoLikeStatistics);
			List<String> videoTitles = favoriteDAO.findAllVideoTitles();
			request.setAttribute("videoTitles", videoTitles);
			request.setAttribute("activeTab", "favorites");
			request.getRequestDispatcher("/html/reportManage.jsp").forward(request, response);
		} catch (Exception e) {
			handleError(request, response, e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String activeTab = request.getParameter("activeTab");
			String videoTitle = request.getParameter("videoTitle");

			List<Object[]> videoLikeStatistics = favoriteDAO.findVideoFavoriteStatistics();
			List<String> videoTitles = favoriteDAO.findAllVideoTitles();
			request.setAttribute("videoLikeStatistics", videoLikeStatistics);
			request.setAttribute("videoTitles", videoTitles);
			switch (activeTab) {
			case "favoriteuser":
				handleFavoriteUsersTab(request, videoTitle);
				break;
			case "shareUser":
				handleShareUsersTab(request, videoTitle);
				break;
			default:
				request.setAttribute("activeTab", "favorites");
			}
			request.getRequestDispatcher("/html/reportManage.jsp").forward(request, response);
		} catch (Exception e) {
			handleError(request, response, e);
		}
	}

	private void handleFavoriteUsersTab(HttpServletRequest request, String videoTitle) throws Exception {
		if (videoTitle != null && !videoTitle.isEmpty()) {
			List<Object[]> favoriteUsers = favoriteDAO.findVideoFavoriteUserStatistics(videoTitle);
			request.setAttribute("favoriteUsers", favoriteUsers);
			request.setAttribute("selectedVideoTitle", videoTitle);
		} else {
			request.setAttribute("favoriteUsers", null);
		}
		request.setAttribute("activeTab", "favoriteuser");
	}

	private void handleShareUsersTab(HttpServletRequest request, String videoTitle) throws Exception {
		if (videoTitle != null && !videoTitle.isEmpty()) {
			List<Object[]> shareUsers = shareDAO.findShareStatisticsByVideoTitle(videoTitle);
			request.setAttribute("shareUsers", shareUsers);
			request.setAttribute("selectedVideoTitle", videoTitle);
		} else {
			request.setAttribute("shareUsers", null);
		}
		request.setAttribute("activeTab", "shareUser");
	}

	private void handleError(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws ServletException, IOException {
		request.setAttribute("error", "Error processing request: " + e.getMessage());
		request.getRequestDispatcher("/html/reportManage.jsp").forward(request, response);
	}
}
