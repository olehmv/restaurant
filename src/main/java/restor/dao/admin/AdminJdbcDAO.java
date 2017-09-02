package restor.dao.admin;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import restor.dao.RestorDAO;
import restor.dto.admin.Admin;
@Component
public class AdminJdbcDAO extends RestorDAO<Admin> implements IAdminDAO {
	private String insert = "insert into admin (name) values(?);";
	private String update = "update admin set name=? where id=?";
	private String delete = "delete from admin where id=?";
	private String fetchAdmins = "select * from admin";
	private String fetchAdmin = "select * from admin where id=?";
	// delete static after test
	private static List<Admin> admins;
	// delete static after test
	private static Admin admin;

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/restaurant";
			con = DriverManager.getConnection(url, "root", "");
			IAdminDAO dao = new AdminJdbcDAO();
			Admin a = dao.fetchAdmin(1);
			System.out.println(a);
			List<Admin> adms = dao.fetchAdmins();
			System.out.println(adms);
			con.close();
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

	@Override
	public Admin update(Admin dto) {
		try {
			ps = con.prepareStatement(update);
			ps.setString(1, dto.getName());
			ps.setInt(2, dto.getId());

			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("Updated");
			} else {
				System.out.println("not Updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Admin delete(Admin dto) {
		try {
			ps = con.prepareStatement(delete);
			ps.setInt(1, dto.getId());
			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("deleted");
			} else {
				System.out.println("not deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Admin insert(Admin dto) {

		try {
			ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getName());

			ResultSet rs = ps.getGeneratedKeys();

			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("Inserted");
			} else {
				System.out.println("not Inserted");
			}
			rs = ps.getGeneratedKeys();
			rs.next();
			int auto_id = rs.getInt(1);
			dto.setId(auto_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Admin fetchAdmin(int dto_id) {
		try {
			ps = con.prepareStatement(fetchAdmin);
			ps.setInt(1, dto_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);

				admin = new Admin();
				admin.setId(id);
				admin.setName(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public List<Admin> fetchAdmins() {
		try {
			ps = con.prepareStatement(fetchAdmins);
			ResultSet rs = ps.executeQuery();
			admins = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				admin = new Admin();
				admin.setId(id);
				admin.setName(name);
				admins.add(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admins;
	}

}
