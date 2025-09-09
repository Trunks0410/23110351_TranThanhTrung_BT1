package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import config.JPAConfig;
import entity.User;

public class UserDAO {

	// Phương thức này là cần thiết để xác thực người dùng
	public User findByUsernameAndPassword(String username, String password) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> query = em.createQuery(
					"SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
			query.setParameter("username", username);
			query.setParameter("password", password);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	public User findByUsername(String username) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
}