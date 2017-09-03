package restor.exception;

public class OrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1300998537877386873L;

	@Override
	public String getMessage(){
		return "Order Not Found";
	}
}
