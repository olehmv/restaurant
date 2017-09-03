package restor.exception;

public class MenuNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3496707019224279140L;
	public String getLocalizedMessage(){
		return "Menu Not Found";	
	}
}
