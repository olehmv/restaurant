package restor.exception;

public class ClientNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1300998537877386873L;

	@Override
	public String getMessage(){
		return "Client Not Found";
	}
}