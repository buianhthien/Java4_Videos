package com.poly.dao;

import java.util.List;

import com.poly.controller.EmailUntil;
import com.poly.entity.User;
import com.poly.service.XJPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UserDAOImpl implements UserDAO {
	private EntityManager em;

	public UserDAOImpl() {
		this.em = XJPA.getEntityManager();
	}

	public void close() {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}

	@Override
	public List<User> findAll() {
		String jpql = "SELECT u FROM User u";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		return query.getResultList();
	}

	@Override
	public User findByIdOrEmail(String idOrEmail) {
		try {
			TypedQuery<User> query = em
					.createQuery("SELECT u FROM User u WHERE u.id = :idOrEmail OR u.email = :idOrEmail", User.class);
			query.setParameter("idOrEmail", idOrEmail);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public User findById(String id) {
		return em.find(User.class, id);
	}

	@Override
	public void create(User entity) {
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(entity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void update(User entity) {
		if (entity.getId() == null || entity.getId().isEmpty()) {
			throw new IllegalArgumentException("ID của người dùng không được để trống.");
		}

		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			User existingUser = em.find(User.class, entity.getId());
			if (existingUser == null) {
				throw new IllegalArgumentException("Người dùng với ID: " + entity.getId() + " không tồn tại.");
			}
			existingUser.setId(entity.getId());
			existingUser.setPassword(entity.getPassword());
			existingUser.setEmail(entity.getEmail());
			em.merge(existingUser);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException("Lỗi khi cập nhật người dùng", e);
		}
	}

	@Override
	public void deleteById(String id) {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("ID không được để trống.");
		}

		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			User entity = em.find(User.class, id);
			if (entity == null) {
				throw new IllegalArgumentException("Người dùng với ID: " + id + " không tồn tại.");
			}
			em.remove(entity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException("Lỗi khi xóa người dùng", e);
		}
	}

	@Override
	public List<Object[]> findAllWithDetails() {
		String jpql = "SELECT v.title, COUNT(f), v.isActive FROM Video v LEFT JOIN v.favorites f "
				+ "GROUP BY v.id, v.title, v.isActive";
		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		return query.getResultList();
	}

	@Override
	public void updatePassword(String userId, String newPassword) {
		User user = findById(userId);
		if (user != null) {
			user.setPassword(newPassword);
			update(user);
		}
	}

	public boolean sendEmailToUser(String userId, String subject, String body) {
		User user = findById(userId);
		if (user != null) {
			String email = user.getEmail();
			return EmailUntil.sendEmail(email, subject, body);
		}
		return false;
	}
}
