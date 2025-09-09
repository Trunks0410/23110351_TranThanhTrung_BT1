package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import config.JPAConfig;
import entity.Category;
import java.util.List;

public class CategoryDAO {

	// This method retrieves all categories (for Admin)
	public List<Category> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
		} finally {
			em.close();
		}
	}

	// This method retrieves categories by user ID (for User and Manager)
	public List<Category> findByUserId(int userId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			// Using JPQL to query categories related to a specific user
			TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.user.id = :userId",
					Category.class);
			query.setParameter("userId", userId);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	public Category findById(int id) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.find(Category.class, id);
		} finally {
			em.close();
		}
	}

	public void create(Category category) {
		EntityManager em = JPAConfig.getEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(category);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(Category category) {
		EntityManager em = JPAConfig.getEntityManager();
		em.getTransaction().begin();
		try {
			em.merge(category);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void remove(int id) {
		EntityManager em = JPAConfig.getEntityManager();
		em.getTransaction().begin();
		try {
			Category category = em.find(Category.class, id);
			if (category != null) {
				em.remove(category);
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}
}
