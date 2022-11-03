package lab12;

public class Lab12 {

	public static void main(String[] args) {
		UserDao uDao = new UserDaoFile();
		UserService uService = new UserService(uDao);

		uService.registerUser("marksatin1", "password");
		
		System.out.println(uService.loginUser("marksatin1", "password"));
	}
}
