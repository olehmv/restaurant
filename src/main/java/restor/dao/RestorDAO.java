package restor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class RestorDAO<T> {
	protected static Connection con;
	protected static PreparedStatement ps;
	
	public T save(T dto) {

		return insert(dto);

	}

	public abstract T insert(T dto);

}
