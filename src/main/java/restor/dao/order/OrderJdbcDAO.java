package restor.dao.order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import restor.dao.RestorDAO;
import restor.dto.item.Item;
import restor.dto.item.OrderItem;
import restor.dto.order.Order;

@Component
public class OrderJdbcDAO extends RestorDAO<Order> implements IOrderDAO {
	private String insert = "insert into restor_order (description,price,admin_id,client_id,active) values(?,?,?,?,?)";
	private String update = "update restor_order set description=?, price=?, admin_id=?, client_id=?, active=? where id=?";
	private String delete = "delete from restor_order where id=?";
	private String fetchOrders = "select * from restor_order";
	private String fetchOrder = "select * from restor_order where id=?";
	private String fetchAdminOrders = "select * from restor_order where admin_id=?";
	private String fetchClientOrders = "select * from restor_order where client_id=?";
	private List<Order> orders;

	@Override
	public Order update(Order dto) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(update);
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
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public Order delete(Order dto) {
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
	public Order insert(Order dto) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
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
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public List<Order> fetchOrders() {
		Order order=null;
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(fetchOrders);
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
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public Order fetchOrder(int dto_id) {
		Order order=null;
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(fetchOrder);
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
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<Order> fetchAdminOrders(int dto_id) {
		Order order=null;
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(fetchAdminOrders);
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
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<Order> fetchClientOrders(int dto_id) {
		Order order=null;
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(fetchClientOrders);
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
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}

}
