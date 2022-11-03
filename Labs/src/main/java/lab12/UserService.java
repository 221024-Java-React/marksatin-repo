package lab12;

import java.util.ArrayList;

public class UserService {
	
	private UserDao userDao;
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String registerUser(String username, String password) {
		if (userDao.getUserByUsername(username) != null) {
			throw new UserAlreadyExistsException();
		} else {
			ArrayList<User> userList = userDao.getAllUsers();
			int crntRegId;
			
			if (userList.size() == 0) {
				crntRegId = 0;
			} else {
				User lastRegUser = userList.get(userList.size() - 1);
				crntRegId = lastRegUser.getId() + 1;
			}
			
			User newUser = new User(crntRegId, username, password);
			userDao.addUserToIO(newUser);
			
			return "New user successfully registered!";
		}
	}
	
	public User loginUser(String username, String password) {
		User loginUser = userDao.getUserByUsername(username);
		if (loginUser == null) {
			throw new UserDoesNotExistException();
		}
		
		if (loginUser.getPassword().equals(password)) {
			return loginUser;
		} else {
			return null;
		}
	}
}
