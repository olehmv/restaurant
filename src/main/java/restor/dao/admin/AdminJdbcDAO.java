package restor.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	private List<Admin> admins;

	@Override
	public Admin update(Admin dto) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, dto.getName());
			ps.setInt(2, dto.getId());
			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Admin delete(Admin dto) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setInt(1, dto.getId());
			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Admin insert(Admin dto) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getName());
			ResultSet rs = ps.getGeneratedKeys();
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			int auto_id = rs.getInt(1);
			dto.setId(auto_id);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Admin fetchAdmin(int dto_id) {
		Connection con = getConnection();
		Admin admin = null;
		try {
			PreparedStatement ps = con.prepareStatement(fetchAdmin);
			ps.setInt(1, dto_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				admin = new Admin();
				admin.setId(id);
				admin.setName(name);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public List<Admin> fetchAdmins() {
		Connection con = getConnection();
		Admin admin = null;
		try {
			PreparedStatement ps = con.prepareStatement(fetchAdmins);
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
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admins;
	}

}
