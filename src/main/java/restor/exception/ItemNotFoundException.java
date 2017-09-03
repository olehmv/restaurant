package restor.exception;

public class ItemNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1300998537877386873L;

	@Override
	public String getMessage(){
		return "Item Not Found";
	}
}
