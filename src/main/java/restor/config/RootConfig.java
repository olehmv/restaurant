package restor.config;

import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan(basePackages = { "restor" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
@PropertySource(value = { "/WEB-INF/db.properties" }, ignoreResourceNotFound = false)
public class RootConfig {

	@Autowired
	Environment env;

	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(env.getRequiredProperty("db.driverClassName"));
		dataSource.setJdbcUrl(env.getRequiredProperty("db.databaseurl"));
		dataSource.setUser(env.getRequiredProperty("db.username"));
		dataSource.setPassword(env.getRequiredProperty("db.password"));
		dataSource.setInitialPoolSize(10);
		dataSource.setIdleConnectionTestPeriod(10);
		return dataSource;
	}

}
