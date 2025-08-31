package service;

import java.sql.Date;
import dao.UserDAO;
import model.User;

public class UserService {
	private UserDAO userDAO;

	public UserService() {
		this.userDAO = new UserDAO();
	}

	public boolean checkLogin(String username, String password) {
		return userDAO.checkLogin(username, password);
	}

	public boolean checkExistEmail(String email) {
		return userDAO.checkExistEmail(email);
	}

	public boolean checkExistUsername(String username) {
		return userDAO.checkExistUsername(username);
	}

	public boolean register(String username, String password, String email, String fullname, String phone,
			Date createdDate) {
		User user = new User(username, password, email, fullname, phone, createdDate);
		return userDAO.addUser(user);
	}

	public boolean forgotPassword(String email) {
		if (userDAO.checkExistEmail(email)) {
			String newPassword = generateRandomPassword();
			if (userDAO.updatePassword(email, newPassword)) {
				String subject = "Mật khẩu mới của bạn";
				String body = "Mật khẩu mới của bạn là: " + newPassword;
				EmailService.sendEmail(email, subject, body);

				return true;
			}
		}
		return false;
	}

	private String generateRandomPassword() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder newPassword = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			newPassword.append(chars.charAt((int) (Math.random() * chars.length())));
		}
		return newPassword.toString();
	}

}
