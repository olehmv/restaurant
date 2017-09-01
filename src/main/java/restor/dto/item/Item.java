package restor.dto.item;

public abstract class Item implements IPrice{
	protected int id;
	protected int order_id;
	protected int menu_id;
	protected String description;
	protected double price;	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", order_id=" + order_id + ", menu_id=" + menu_id + ", description=" + description
				+ ", price=" + price + "]";
	}


}
