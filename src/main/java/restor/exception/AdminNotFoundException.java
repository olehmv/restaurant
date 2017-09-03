package restor.exception;

public class AdminNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1300998537877386873L;

	@Override
	public String getMessage(){
		return "Admin Not Found";
	}
}
