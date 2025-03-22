package com.poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dao.FavoriteDAO;
import com.poly.dao.FavoriteDAOImpl;
import com.poly.dao.VideoDAO;
import com.poly.dao.VideoDAOImpl;
import com.poly.entity.Favorite;
import com.poly.entity.User;
import com.poly.entity.Video;

@WebServlet("/myFavoriteServlet")
public class myFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FavoriteDAO favoriteDAO;
	private VideoDAO videoDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		favoriteDAO = new FavoriteDAOImpl();
		videoDAO = new VideoDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<Video> favoriteVideos = favoriteDAO.findFavoriteVideosByUser(user.getId());
			req.setAttribute("favoriteVideos", favoriteVideos);
			req.getRequestDispatcher("/html/myFavorite.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null) {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bạn cần đăng nhập để thực hiện hành động này.");
			return;
		}

		User userlogin = (User) session.getAttribute("user");
		Map<String, Object> responseMap = new HashMap<>();

		if (userlogin == null) {
			responseMap.put("status", "error");
			responseMap.put("message", "Bạn cần đăng nhập để thích video.");
			sendJsonResponse(resp, responseMap);
			return;
		}

		String videoId = req.getParameter("videoId");
		if (videoId != null && !videoId.isEmpty()) {
			Video video = videoDAO.findById(videoId);
			if (video != null) {
				Favorite existingFavorite = favoriteDAO.findFavoriteByUserAndVideo(userlogin.getId(), videoId);
				if (existingFavorite != null) {
					responseMap.put("status", "error");
					responseMap.put("message", "Bạn đã thích video này rồi.");
				} else {
					Favorite favorite = new Favorite();
					favorite.setVideo(video);
					favorite.setUser(userlogin);
					favorite.setLikeDate(new Date(System.currentTimeMillis()));
					favoriteDAO.create(favorite);

					responseMap.put("status", "success");
					responseMap.put("message", "Video đã được thích.");
				}
			} else {
				responseMap.put("status", "error");
				responseMap.put("message", "Video không tồn tại.");
			}
		} else {
			responseMap.put("status", "error");
			responseMap.put("message", "Video không hợp lệ.");
		}

		sendJsonResponse(resp, responseMap);
	}

	private void sendJsonResponse(HttpServletResponse resp, Map<String, Object> responseMap) throws IOException {
		resp.setContentType("application/json");
		resp.getWriter().write(new ObjectMapper().writeValueAsString(responseMap));
	}

	@Override
	public void destroy() {
		favoriteDAO = null;
		videoDAO = null;
		super.destroy();
	}
}
