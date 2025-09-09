package config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {

	private static EntityManagerFactory factory;

	// Sử dụng static initializer block để đảm bảo factory chỉ được khởi tạo một lần
	static {
		try {
			factory = Persistence.createEntityManagerFactory("dataSource");
		} catch (Exception e) {
			// Ghi lại lỗi nếu quá trình khởi tạo thất bại
			e.printStackTrace();
			throw new RuntimeException("Lỗi khởi tạo EntityManagerFactory", e);
		}
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public static void shutdown() {
		if (factory != null) {
			factory.close();
		}
	}
}
