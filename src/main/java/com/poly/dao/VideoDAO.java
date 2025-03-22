package com.poly.dao;

import java.util.List;

import com.poly.entity.Video;

public interface VideoDAO {

	List<Video> findAll();

	Video findById(String id);

	void create(Video video);

	void update(Video video);

	void deleteById(String id);
}
