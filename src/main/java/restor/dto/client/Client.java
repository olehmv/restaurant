package restor.dto.client;

import java.util.List;

import org.springframework.stereotype.Component;

import restor.dto.order.Order;
public  class Client {
	private int id;
	private String name;
	private List<Order> orders;
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
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	

}
