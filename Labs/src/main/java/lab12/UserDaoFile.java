package lab12;

import java.util.ArrayList;

public class UserDaoFile implements UserDao {
	
	private FileIO<ArrayList<User>> io;
	
	public UserDaoFile() {
		this.io = new FileIO<ArrayList<User>>("userData.txt");
	}
	
	@Override
	public void addUserToIO(User u) {
		ArrayList<User> userList = io.readObject();
		
		if (userList == null) {
			userList = new ArrayList<User>();
		}
		
		userList.add(u);
		io.writeObject(userList);
	}

	@Override
	public ArrayList<User> getAllUsers() {
		ArrayList<User> userList = io.readObject();
		
		if (userList == null) {
			userList = new ArrayList<>();
		}
		
		return userList;
	}

	@Override
	public User getUserByUsername(String username) {
		ArrayList<User> userList = io.readObject();
		
		if (userList == null) {
			userList = new ArrayList<>();
		}
		
		for (User u : userList) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}
}
