package restor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import restor.dao.AdminJdbcDAO;
import restor.dao.IAdminDAO;
import restor.dto.admin.Admin;
import restor.dto.admin.RestorAdmin;

public class Main {

	static Connection conn;

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/restaurant";
			conn = DriverManager.getConnection(url, "root", "");
			Admin admin = new RestorAdmin();
			admin.setName("oleg");
			 IAdminDAO dao = new AdminJdbcDAO();
			 Admin save = dao.save(admin);
			 System.out.println(save);
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.err.println(ex.getMessage());
		} catch (IllegalAccessException ex) {
			System.err.println(ex.getMessage());
		} catch (InstantiationException ex) {
			System.err.println(ex.getMessage());
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
