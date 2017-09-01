package restor.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import restor.dto.item.Item;
import restor.dto.item.MenuItem;
import restor.dto.menu.Menu;

public class MenuJdbcDAO extends RestorDAO<Menu> implements IMenuDAO {
	private String insert = "insert into menu (description) values(?)";
	private String update = "update menu set description=? where id=?";
	private String delete = "delete from menu where id=?";
	private String fetchItems = "select * from item where menu_id=?";
	private Item item;
	private List<Item>items;
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/restaurant";
			con = DriverManager.getConnection(url, "root", "");
			Menu admin = new Menu();
			admin.setDescription("oleg");
			IMenuDAO dao = new MenuJdbcDAO();
			Menu save = dao.save(admin);
			System.out.println(save);
			admin.setDescription("Petro");
			Menu update = dao.update(admin);
			System.out.println(update);
			Menu delete = dao.delete(admin);
			System.out.println(delete);
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
	public Menu update(Menu dto) {
		try {
			ps = con.prepareStatement(update);
			ps.setString(1, dto.getDescription());
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
	public Menu delete(Menu dto) {
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
	public Menu insert(Menu dto) {
		try {

			ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public List<Item> fetchItems(int dto_id) {
		try {
			ps = con.prepareStatement(fetchItems);
			ps.setInt(1, dto_id);
			ResultSet rs = ps.executeQuery();
			items=new ArrayList<>();
			while(rs.next()){
			int id = rs.getInt(1);
			int menu_id = rs.getInt(2);
			int order_id = rs.getInt(3);
			String description = rs.getString(4);
			double price= rs.getDouble(5);
			item=new MenuItem();
			item.setDescription(description);
			item.setId(id);
			item.setMenu_id(menu_id);
			item.setOrder_id(order_id);
			item.setPrice(price);
			items.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}
}
