package restor.dao.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import restor.dao.RestorDAO;
import restor.dto.item.Item;
import restor.dto.item.MenuItem;
import restor.dto.item.OrderItem;

@Component
public class ItemJdbcDAO extends RestorDAO<Item> implements IItemDAO {
	private String insert = "insert into item (menu_id,order_id,description,price)values(?,?,?,?)";
	private String update = "update item set menu_id=?, order_id=?, description=?, price=? where id=?";
	private String delete = "delete from item where id=?";
	private String fetchItems = "select * from item";
	private String fetchItem = "select * from item where id=?";
	private String fetchMenuItems = "select * from item where menu_id=? and order_id is null";
	private String fetchOrderItems = "select * from item where order_id=?";
	private List<Item> items;

	@Override
	public Item update(Item dto) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setInt(1, dto.getMenu_id());
			ps.setInt(2, dto.getOrder_id());
			ps.setString(3, dto.getDescription());
			ps.setDouble(4, dto.getPrice());
			ps.setInt(5, dto.getId());
			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Item delete(Item dto) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = getConnection().prepareStatement(delete);
			ps.setInt(1, dto.getId());
			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Item insert(Item dto) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, dto.getMenu_id());
			ps.setInt(2, dto.getOrder_id());
			ps.setString(3, dto.getDescription());
			ps.setDouble(4, dto.getPrice());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
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
	public List<Item> fetchItems() {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(fetchItems);
			ResultSet rs = ps.executeQuery();
			items = new ArrayList<>();
			while (rs.next()) {
				Item item = null;
				int id = rs.getInt(1);
				int menu_id = rs.getInt(2);
				int order_id = rs.getInt(3);
				String description = rs.getString(4);
				double price = rs.getDouble(5);
				item = new OrderItem();
				item.setDescription(description);
				item.setId(id);
				item.setMenu_id(menu_id);
				item.setOrder_id(order_id);
				item.setPrice(price);
				items.add(item);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public List<Item> fetchOrderItems(int dto_id) {
		Connection con = getConnection();
		Item item = null;
		try {
			PreparedStatement ps = con.prepareStatement(fetchOrderItems);
			ps.setInt(1, dto_id);
			ResultSet rs = ps.executeQuery();
			items = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				int menu_id = rs.getInt(2);
				int order_id = rs.getInt(3);
				String description = rs.getString(4);
				double price = rs.getDouble(5);
				item = new OrderItem();
				item.setDescription(description);
				item.setId(id);
				item.setMenu_id(menu_id);
				item.setOrder_id(order_id);
				item.setPrice(price);
				items.add(item);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public List<Item> fetchMenuItems(int dto_id) {
		Connection con = getConnection();
		Item item = null;
		try {
			PreparedStatement ps = con.prepareStatement(fetchMenuItems);
			ps.setInt(1, dto_id);
			ResultSet rs = ps.executeQuery();
			items = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				int menu_id = rs.getInt(2);
				String description = rs.getString(4);
				double price = rs.getDouble(5);
				item = new MenuItem();
				item.setDescription(description);
				item.setId(id);
				item.setMenu_id(menu_id);
				item.setPrice(price);
				items.add(item);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public Item fetchItem(int dto_id) {
		Connection con = getConnection();
		Item item = null;
		try {
			PreparedStatement ps = con.prepareStatement(fetchItem);
			ps.setInt(1, dto_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				int menu_id = rs.getInt(2);
				int order_id = rs.getInt(3);
				String description = rs.getString(4);
				double price = rs.getDouble(5);
				item = new Item();
				item.setDescription(description);
				item.setId(id);
				item.setMenu_id(menu_id);
				item.setOrder_id(order_id);
				item.setPrice(price);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

}
