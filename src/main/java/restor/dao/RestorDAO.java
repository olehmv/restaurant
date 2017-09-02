package restor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public abstract class RestorDAO<T> {
	@Autowired
	protected  Connection con;
	protected static PreparedStatement ps;
	
	public T save(T dto) {

		return insert(dto);

	}

	public abstract T insert(T dto);

}
