package com.poly.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Video")
@NamedQueries({ @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v"),
		@NamedQuery(name = "Video.findAllActive", query = "SELECT v FROM Video v WHERE v.active = true"),
		@NamedQuery(name = "Video.findAllWithLikeCount", query = "SELECT v.title, COUNT(f) AS likeCount, MIN(f.likeDate) AS firstLikeDate, MAX(f.likeDate) AS lastLikeDate "
				+ "FROM Video v LEFT JOIN Favorite f ON v.id = f.video.id " + "GROUP BY v.id, v.title"),

		@NamedQuery(name = "Video.findByTitleWithLikeCount", query = "SELECT v.title, COUNT(f) AS likeCount, v.active "
				+ "FROM Video v LEFT JOIN Favorite f ON v.id = f.video.id " + "WHERE v.title LIKE :title "
				+ "GROUP BY v.id, v.title, v.active"),
		@NamedQuery(name = "Video.findLikedVideosByUserId", query = "SELECT v FROM Favorite f JOIN f.video v WHERE f.user.id = :userId") })
public class Video {
	@Id
	private String id;
	private String link;
	private String title;
	private String poster;
	private String description;
	private boolean active;
	private int views;
	@OneToMany(mappedBy = "video")
	private List<Favorite> favorite;

	public Video() {
	}

	public Video(String id, String link, String title, String poster, String description, boolean active, int views,
			List<Favorite> favorite) {
		super();
		this.id = id;
		this.link = link;
		this.title = title;
		this.poster = poster;
		this.description = description;
		this.active = active;
		this.views = views;
		this.favorite = favorite;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public List<Favorite> getFavorite() {
		return favorite;
	}

	public void setFavorite(List<Favorite> favorite) {
		this.favorite = favorite;
	}

}