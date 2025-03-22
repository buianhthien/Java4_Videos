package com.poly.dao;

import java.util.List;

import com.poly.entity.Share;

public interface ShareDAO {

	List<Share> findAll();

	Share findById(String id);

	void create(Share share);

	void update(Share share);

	void deleteById(String id);

	List<Object[]> findVideoShareStatistics();

	List<String> findAllVideoTitles();

	List<Object[]> findShareStatisticsByVideoTitle(String videoTitle);

}
