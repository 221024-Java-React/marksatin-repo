package lab15.exceptions;

public class PersonDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersonDoesNotExistException() {
		super("The person you are searching for does not exist.");
	}
}
