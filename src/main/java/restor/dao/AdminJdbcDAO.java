package restor.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import restor.dto.admin.Admin;
import restor.dto.admin.RestorAdmin;
import restor.dto.order.Order;

public class AdminJdbcDAO extends RestorDAO<Admin> implements IAdminDAO {
	private String insert = "insert into admin (name) values(?);";
	private String update = "update admin set name=? where id=?";
	private String delete = "delete from admin where id=?";
	private String fetchOrders = "select * from restor_order where admin_id=?";
	private String fetchOrder = "select * from restor_order where client_id=?";
	//delete  static after test
	private static List<Order> orders;
	//delete  static after test
	private static Order order;
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/restaurant";
			con = DriverManager.getConnection(url, "root", "");
			Admin admin = new RestorAdmin();
			admin.setName("oleg");
			IAdminDAO dao = new AdminJdbcDAO();
			Admin save = dao.save(admin);
			System.out.println(save);
			admin.setName("Petro");
			Admin update = dao.update(admin);
			System.out.println(update);
			orders=dao.fetchOrders(admin.getId());
			System.out.println(orders);
			order=dao.fetchOrder(orders.get(0).getClient_id());
			System.out.println(order);
			// Admin delete = dao.delete(admin);
			// System.out.println(delete);
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
	public List<Order> fetchOrders(int dto_id) {
		try {
			ps = con.prepareStatement(fetchOrders);
			ps.setInt(1, dto_id);
			ResultSet rs = ps.executeQuery();
			orders = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String description = rs.getString(2);
				double price = rs.getDouble(3);
				int admin_id = rs.getInt(4);
				int client_id = rs.getInt(5);
				boolean active = rs.getBoolean(6);
				order = new Order();
				order.setId(id);
				order.setDescription(description);
				order.setPrice(price);
				order.setAdmin_id(admin_id);
				order.setClient_id(client_id);
				order.setActive(active);
				orders.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;

	}

	@Override
	public Order fetchOrder(int dto_id) {
		try {
			ps = con.prepareStatement(fetchOrder);
			ps.setInt(1, dto_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String description = rs.getString(2);
				double price = rs.getDouble(3);
				int admin_id = rs.getInt(4);
				int client_id = rs.getInt(5);
				boolean active = rs.getBoolean(6);
				order = new Order();
				order.setId(id);
				order.setDescription(description);
				order.setPrice(price);
				order.setAdmin_id(admin_id);
				order.setClient_id(client_id);
				order.setActive(active);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

}
