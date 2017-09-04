package restor.dto.menu;

import java.util.List;

import restor.dto.item.Item;

public class Menu {
	protected int id;
	protected String description;
	private List<Item> menuItems;

	public Menu() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", description=" + description + "]";
	}

	public List<Item> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<Item> menuItems) {
		this.menuItems = menuItems;
	}

}
