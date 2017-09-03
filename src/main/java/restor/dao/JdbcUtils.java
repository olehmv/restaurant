package restor.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JdbcUtils {
	private final static Logger logger=Logger.getLogger(JdbcUtils.class);
	@Autowired
	private DataSource dataSource;
	 public Connection getConnention() {
		 try {
				
				logger.info("Connection Initialize...");
				return dataSource.getConnection();
				
			} catch (Exception e) {
				logger.error("Error initializing Connection :"+e.getMessage());
				throw new ExceptionInInitializerError(e);
					
			}
		 
	 }
	

}
