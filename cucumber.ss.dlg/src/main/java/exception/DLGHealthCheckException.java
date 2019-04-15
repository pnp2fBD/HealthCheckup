package exception;

public class DLGHealthCheckException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DLGHealthCheckException() {
		super();
	}

	public DLGHealthCheckException(String message) {
		super(message);
	}
	
	 public DLGHealthCheckException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
