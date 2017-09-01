package restor.dto.client;

import java.util.List;

import restor.dto.order.Order;

public abstract class Client {
	private int id;
	protected String name;
	protected List<Order> orders;
	protected Order order;

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
	
	

}
