package restor.dao.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import restor.dao.RestorDAO;
import restor.dto.menu.Menu;
import restor.exception.MenuNotFoundException;

@Component
public class MenuJdbcDAO extends RestorDAO<Menu> implements IMenuDAO {
	private String insert = "insert into menu (description) values(?)";
	private String update = "update menu set description=? where id=?";
	private String delete = "delete from menu where id=?";
	private String fetchMenus = "select * from menu";
	private String fetchMenu = "select * from menu where id=?";
	private List<Menu> menus;

	@Override
	public Menu update(Menu dto) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setString(1, dto.getDescription());
			ps.setInt(2, dto.getId());

			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("Updated");
			} else {
				System.out.println("not Updated");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Menu delete(Menu dto) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(delete);
			ps.setInt(1, dto.getId());
			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("deleted");
			} else {
				System.out.println("not deleted");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Menu insert(Menu dto) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getDescription());
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
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Menu fetchMenu(int dto_id) {
		Menu menu = null;
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(fetchMenu);
			ps.setInt(1, dto_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String description = rs.getString(2);
				menu = new Menu();
				menu.setDescription(description);
				menu.setId(id);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public List<Menu> fetchMenus() {
		Menu menu = null;
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(fetchMenus);
			ResultSet rs = ps.executeQuery();
			menus = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String description = rs.getString(2);
				menu = new Menu();
				menu.setDescription(description);
				menu.setId(id);
				menus.add(menu);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menus;
	}
}
