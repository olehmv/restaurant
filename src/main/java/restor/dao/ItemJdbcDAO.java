package restor.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import restor.dto.item.DishItem;
import restor.dto.item.Item;

public class ItemJdbcDAO extends RestorDAO<Item> implements IItemDAO {
	private String insert = "insert into item (menu_id,order_id,description,price)values(?,?,?,?)";
	private String update = "update item set menu_id=?, order_id=?, description=?, price=? where id=?";
	private String delete = "delete from item where id=?";

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/restaurant";
			con = DriverManager.getConnection(url, "root", "");
			Item item = new DishItem();
			item.setDescription("Hello");
			item.setMenu_id(1);
			item.setOrder_id(2);
			item.setPrice(15.36);
			IItemDAO dao = new ItemJdbcDAO();
			Item save = dao.save(item);
			System.out.println(save);
			item.setDescription("updated");
			item.setMenu_id(2);
			item.setOrder_id(6);
			item.setPrice(30.22);
			Item updated = dao.update(item);
			System.out.println(updated);
			Item deleted = dao.delete(item);
			System.out.println(deleted);

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
	public Item update(Item dto) {
		try {
			ps = con.prepareStatement(update);
			ps.setInt(1, dto.getMenu_id());
			ps.setInt(2, dto.getOrder_id());
			ps.setString(3, dto.getDescription());
			ps.setDouble(4, dto.getPrice());
			ps.setInt(5, dto.getId());

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
	public Item delete(Item dto) {
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
	public Item insert(Item dto) {
		try {
			ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, dto.getMenu_id());
			ps.setInt(2, dto.getOrder_id());
			ps.setString(3, dto.getDescription());
			ps.setDouble(4, dto.getPrice());

			int i = ps.executeUpdate();
			if (i != 0) {
				System.out.println("Inserted");
			} else {
				System.out.println("not Inserted");
			}
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int auto_id = rs.getInt(1);
			dto.setId(auto_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

}
