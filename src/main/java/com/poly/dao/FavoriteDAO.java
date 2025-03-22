package com.poly.dao;

import java.util.List;

import com.poly.entity.Favorite;
import com.poly.entity.Video;

public interface FavoriteDAO {

	List<Favorite> findAll();

	Favorite findById(String id);

	void create(Favorite item);

	void update(Favorite item);

	void deleteById(String id);

	List<Object[]> findVideoFavoriteStatistics();

	List<Object[]> findVideoFavoriteUserStatistics(String videoTitle);

	List<String> findAllVideoTitles();

	Favorite findFavoriteByUserAndVideo(String id, String videoId);

	List<Video> findFavoriteVideosByUser(String id);

}
