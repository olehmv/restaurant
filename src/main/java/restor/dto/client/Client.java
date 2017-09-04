package restor.dto.client;

import java.util.List;

import restor.dto.order.Order;

public class Client {
	private int id;
	private String name;
	private List<Order> clientOrders;
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
		return "Client [id=" + id + ", name=" + name + "]";
	}

	public List<Order> getClientOrders() {
		return clientOrders;
	}

	public void setClientOrders(List<Order> clientOrders) {
		this.clientOrders = clientOrders;
	}

}
