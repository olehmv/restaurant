package restor.dto.order;

import java.util.List;

import restor.dto.admin.IProcess;
import restor.dto.item.Item;

public class Order implements IProcess {

	private int id;
	private int admin_id;
	private int client_id;
	private String description;
	private double price;
	private boolean active;
	private List<Item> orderItems;

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double price() {
		
		return 0;
	}

	public List<Item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Item> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public Order process(String description, List<Item> items) {
		for (Item item : items) {
			setPrice(getPrice() + item.price());
		}
		return this;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", admin_id=" + admin_id + ", client_id=" + client_id + ", description="
				+ description + ", price=" + price + ", active=" + active + "]";
	}
	

}
