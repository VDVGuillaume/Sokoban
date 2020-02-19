package exceptions;


/*GVDV made this class to throw exceptions on the validatePassword method 
 * not sure which messages to use yet*/

public class PasswordException extends RuntimeException{

	public PasswordException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PasswordException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PasswordException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PasswordException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
	

}
