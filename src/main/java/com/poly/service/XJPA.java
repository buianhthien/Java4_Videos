package com.poly.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJPA {
	private static EntityManagerFactory factory;
	static {
		factory = Persistence.createEntityManagerFactory("Assignment_PD11347_BAT");
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
