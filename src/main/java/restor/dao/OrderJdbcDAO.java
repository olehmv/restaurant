package restor.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import restor.dto.item.Item;
import restor.dto.item.OrderItem;
import restor.dto.order.Order;

public class OrderJdbcDAO extends RestorDAO<Order> implements IOderDAO {
	private String insert = "insert into restor_order (description,price,admin_id,client_id,active) values(?,?,?,?,?)";
	private String update = "update restor_order set description=?, price=?, admin_id=?, client_id=?, active=? where id=?";
	private String delete = "delete from restor_order where id=?";
	private String fetchItems = "select * from item where client_id=?";
	private Item item;
	private List<Item>items;
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/restaurant";
			con = DriverManager.getConnection(url, "root", "");
			Order item = new Order();
			item.setDescription("Hello");
			item.setAdmin_id(1);
			item.setClient_id(2);
			item.setPrice(15.36);
			IOderDAO dao = new OrderJdbcDAO();
			Order save = dao.save(item);
			System.out.println(save);
			item.setDescription("updated");
			item.setClient_id(2);
			item.setAdmin_id(5);
			item.setPrice(30.22);
			Order updated = dao.update(item);
			System.out.println(updated);
			// Order deleted = dao.delete(item);
			// System.out.println(deleted);

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
	public Order update(Order dto) {
		try {
			ps = con.prepareStatement(update);
			ps.setString(1, dto.getDescription());
			ps.setDouble(2, dto.getPrice());
			ps.setInt(3, dto.getAdmin_id());
			ps.setInt(4, dto.getClient_id());
			ps.setBoolean(5, dto.isActive());
			ps.setInt(6, dto.getId());

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
	public Order delete(Order dto) {
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
	public Order insert(Order dto) {
		try {
			ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getDescription());
			ps.setDouble(2, dto.getPrice());
			ps.setInt(3, dto.getAdmin_id());
			ps.setInt(4, dto.getClient_id());
			ps.setBoolean(5, dto.isActive());


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
			item=new OrderItem();
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