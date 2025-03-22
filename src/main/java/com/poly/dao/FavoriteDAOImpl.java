package com.poly.dao;

import java.util.ArrayList;
import java.util.List;

import com.poly.entity.Favorite;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.XJPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class FavoriteDAOImpl implements FavoriteDAO {
	EntityManager em = XJPA.getEntityManager();

	@Override
	protected void finalize() throws Throwable {
		em.close();
	}

	@Override
	public List<Favorite> findAll() {
		String jpql = "SELECT f FROM Favorite f";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		return query.getResultList();
	}

	@Override
	public Favorite findById(String id) {
		return em.find(Favorite.class, id);
	}

	@Override
	public void create(Favorite entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(Favorite entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void deleteById(String id) {
		Favorite entity = em.find(Favorite.class, id);
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public List<Object[]> findVideoFavoriteStatistics() {
		String jpql = "SELECT v.title, COUNT(f), MAX(f.likeDate), MIN(f.likeDate) " + "FROM Favorite f JOIN f.video v "
				+ "GROUP BY v.title";
		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		return query.getResultList();
	}

	@Override
	public List<String> findAllVideoTitles() {
		String jpql = "SELECT v.title FROM Video v";
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		return query.getResultList();
	}

	@Override
	public List<Object[]> findVideoFavoriteUserStatistics(String videoTitle) {
		String jpql = "SELECT u.id, u.fullname, u.email, f.likeDate " + "FROM Favorite f " + "JOIN f.user u "
				+ "JOIN f.video v " + "WHERE v.title = :videoTitle";
		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		query.setParameter("videoTitle", videoTitle);
		return query.getResultList();
	}

	public Favorite findFavoriteByUserAndVideo(String userId, String videoId) {
		String query = "SELECT f FROM Favorite f WHERE f.user.id = :userId AND f.video.id = :videoId";
		try {
			return em.createQuery(query, Favorite.class).setParameter("userId", userId).setParameter("videoId", videoId)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public List<Favorite> findFavoritesByUser(String userId) {
		String query = "SELECT f FROM Favorite f WHERE f.user.id = :userId";
		return em.createQuery(query, Favorite.class).setParameter("userId", userId).getResultList();
	}

	public List<Video> findFavoriteVideosByUser(String userId) {
		String jpql = "SELECT f.video FROM Favorite f WHERE f.user.id = :userId";
		try {
			TypedQuery<Video> query = em.createQuery(jpql, Video.class);
			query.setParameter("userId", userId);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
