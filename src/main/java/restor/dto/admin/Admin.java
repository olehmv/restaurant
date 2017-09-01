package restor.dto.admin;

import java.util.List;

import org.springframework.stereotype.Component;

import restor.dto.item.IPrice;
import restor.dto.order.Order;

public abstract class Admin implements IProcess, IPrice {
	private int id;
	protected String name;
	protected List<Order> orders;
	private Order order;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
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

}
