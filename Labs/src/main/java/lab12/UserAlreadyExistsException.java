package lab12;

public class UserAlreadyExistsException extends RuntimeException {

	public UserAlreadyExistsException() {
		super("This user already exists.");
	}
	
}