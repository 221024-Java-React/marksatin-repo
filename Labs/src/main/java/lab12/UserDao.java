package lab12;

import java.util.ArrayList;

public interface UserDao {
	
	public void addUserToIO(User u);
	public ArrayList<User> getAllUsers();
	public User getUserByUsername(String username);
}
