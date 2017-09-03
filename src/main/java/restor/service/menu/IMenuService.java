package restor.service.menu;

import java.util.List;

import restor.dto.item.Item;
import restor.dto.menu.Menu;

public interface IMenuService {
	/**
	 * Search Menu by id
	 * @param dto_id
	 * @return Menu
	 */
	Menu fetchMenu(int dto_id);
	/**
	 * Add Menu to database
	 * @param dto
	 * @return Menu entity
	 */
	Menu addMenu(Menu dto);
	/**
	 * Delete Menu from database
	 * @param dto
	 * @return deleted menu
	 */
	Menu deleteMenu(Menu dto);
	/**
	 * Update Menu entity
	 * @param dto
	 * @return updated Menu entity
	 */
	Menu updateMenu(Menu dto);
	/**
	 * Show all Menu from database
	 * @return List<Menu>
	 */
	List<Menu>fetchMenus();
	/**
	 * Show all Items from Menu
	 * @param dto_id
	 * @return List<Item>
	 */
	List<Item>fetchItems(int dto_id);
	/**
	 * Add Items to Menu
	 * @param List<Item>
	 * @return
	 */
	List<Item> addItems(List<Item>dtos);
	/**
	 * Deletes Item from Menu
	 * @param List<Item>
	 * @return updated items
	 */
	List<Item>deleteItems(List<Item>dtos);
	/**
	 * Update Menu Items
	 * @paramList<Item>
	 * @return updated items
	 */
	List<Item>updateItems(List<Item>dtos);
	
	
}
