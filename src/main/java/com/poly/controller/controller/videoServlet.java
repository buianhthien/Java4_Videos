package com.poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.dao.VideoDAO;
import com.poly.dao.VideoDAOImpl;
import com.poly.entity.Video;

@WebServlet({ "/video", "/video/edit/*", "/video/create", "/video/update", "/video/delete", "/video/reset" })
@MultipartConfig
public class videoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "uploads";
	private VideoDAO dao = new VideoDAOImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Video form = new Video();
		String message = "Nhập thông tin video";
		List<Video> list = new ArrayList<>();
		try {
			BeanUtils.populate(form, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		String path = req.getServletPath();

		try {
			if (path.contains("edit")) {
				String id = req.getPathInfo().substring(1);
				form = dao.findById(id);
				if (form != null) {
					message = "Edit: " + id;
				} else {
					message = "ID không tồn tại: " + id;
				}
			} else if (path.contains("create")) {
				String posterPath = savePosterFile(req);
				form.setPoster(posterPath);
				dao.create(form);
				message = "Created successfully: " + form.getId();
				form = new Video();
			} else if (path.contains("update")) {
				if (form.getId() == null || form.getId().trim().isEmpty()) {
					throw new IllegalArgumentException("ID của video không được để trống.");
				}
				String posterPath = savePosterFile(req);
				if (posterPath != null) {
					form.setPoster(posterPath);
				}
				dao.update(form);
				message = "Updated successfully: " + form.getId();
				form = new Video();
			} else if (path.contains("delete")) {
				String id = req.getParameter("id");
				if (id != null && !id.isEmpty()) {
					dao.deleteById(id);
					message = "Deleted successfully: " + id;
				} else {
					message = "ID không được để trống khi xóa.";
				}
				form = new Video();
			} else if (path.contains("reset")) {
				form = new Video();
			}
			list = dao.findAll();
		} catch (Exception e) {
			message = "Đã xảy ra lỗi: " + e.getMessage();
			e.printStackTrace();
		}
		req.setAttribute("message", message);
		req.setAttribute("video", form);
		req.setAttribute("videos", list);

		req.getRequestDispatcher("/html/videoManage.jsp").forward(req, resp);
	}

	private String savePosterFile(HttpServletRequest req) throws IOException, ServletException {
		Part filePart = req.getPart("poster");
		if (filePart != null && filePart.getSize() > 0) {
			String fileName = filePart.getSubmittedFileName();
			String appPath = getServletContext().getRealPath("");
			String savePath = appPath + File.separator + SAVE_DIR;

			File uploadDir = new File(savePath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			String filePath = savePath + File.separator + fileName;
			filePart.write(filePath);
			return SAVE_DIR + "/" + fileName;
		}
		return null;
	}
}