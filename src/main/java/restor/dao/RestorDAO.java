package restor.dao;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class RestorDAO<T> {
	@Autowired
	private JdbcUtils jdbcUtils;

	public T save(T dto) {

		return insert(dto);

	}

	public abstract T insert(T dto);

	public Connection getConnection() {
		return jdbcUtils.getConnention();
	}

}
