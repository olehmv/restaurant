package restor.dao.item;

import java.sql.DriverManager;
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
	private String fetchMenuItems = "select * from item where menu_id=?";
	private String fetchOrderItems = "select * from item where order_id=?";

	private List<Item> items;
	private Item item; 
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/restaurant";
			con = DriverManager.getConnection(url, "root", "");
			IItemDAO dao = new ItemJdbcDAO();
			Item i = dao.fetchItem(1);
			System.out.println(i+"\n");
			List<Item> is = dao.fetchItems();
			System.out.println(is+"\n");
			List<Item> mis = dao.fetchMenuItems(1);
			System.out.println(mis+"\n");
			List<Item> ois = dao.fetchOrderItems(1);
			System.out.println(ois);
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

	@Override
	public List<Item> fetchItems() {
		try {
			ps = con.prepareStatement(fetchItems);
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

	@Override
	public List<Item> fetchOrderItems(int dto_id) {
		try {
			ps = con.prepareStatement(fetchOrderItems);
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

	@Override
	public List<Item> fetchMenuItems(int dto_id) {
		try {
			ps = con.prepareStatement(fetchMenuItems);
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

	@Override
	public Item fetchItem(int dto_id) {
		try {
			ps = con.prepareStatement(fetchItem);
			ps.setInt(1, dto_id);
			ResultSet rs = ps.executeQuery();
			items=new ArrayList<>();
			while(rs.next()){
			int id = rs.getInt(1);
			int menu_id = rs.getInt(2);
			int order_id = rs.getInt(3);
			String description = rs.getString(4);
			double price= rs.getDouble(5);
			item=new Item();
			item.setDescription(description);
			item.setId(id);
			item.setMenu_id(menu_id);
			item.setOrder_id(order_id);
			item.setPrice(price);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

}
