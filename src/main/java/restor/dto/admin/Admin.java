package restor.dto.admin;

import java.util.List;

import org.springframework.stereotype.Component;

import restor.dto.item.IPrice;
import restor.dto.item.Item;
import restor.dto.order.Order;
@Component
public  class Admin implements IProcess, IPrice {
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

	@Override
	public Order process(String description, List<Item> items) {
		setOrder(getOrder().process(description, items));
		getOrders().add(getOrder());
		return getOrder();
	}

	@Override
	public double price() {
		return getOrder().getPrice();
	}

}
