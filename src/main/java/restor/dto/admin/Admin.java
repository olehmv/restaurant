package restor.dto.admin;

import java.util.List;

import restor.dto.order.Order;

public class Admin {
	private int id;
	protected String name;
	private List<Order> adminOrders;
	private Order order;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Admin [name=" + name + " id: " + id + "]";
	}

	public List<Order> getAdminOrders() {
		return adminOrders;
	}

	public void setAdminOrders(List<Order> adminOrders) {
		this.adminOrders = adminOrders;
	}

}
