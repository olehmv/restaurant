package restor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class RestorDAO<T> {
	static Connection con;
	static PreparedStatement ps;

	public T save(T dto) {

		return insert(dto);

	}

	public abstract T insert(T dto);

}
