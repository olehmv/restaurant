package restor.service.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restor.dao.item.IItemDAO;
import restor.dao.menu.IMenuDAO;
import restor.dto.item.Item;
import restor.dto.menu.Menu;

@Component
public class MenuService implements IMenuService {
	@Autowired
	private IMenuDAO menuDao;
	@Autowired
	private IItemDAO itemDao;

	@Override
	public Menu fetchMenu(int dto_id) {
		Menu dto = menuDao.fetchMenu(dto_id);
		if (dto == null)
			return dto;
		List<Item> items = fetchItems(dto_id);
		dto.setMenuItems(items);
		return dto;
	}

	@Override
	public Menu addMenu(Menu dto) {
		Menu menu = menuDao.save(dto);
		List<Item> menuItems = dto.getMenuItems();
		for (Item item : menuItems) {
			item.setMenu_id(menu.getId());
			item.setOrder_id(0);
		}
		List<Item> items = addItems(menuItems);
		menu.setMenuItems(items);
		return menu;
	}

	@Override
	public Menu deleteMenu(Menu dto) {
		menuDao.delete(dto);
		List<Item> updatedItem = deleteItems(dto.getMenuItems());
		dto.setMenuItems(updatedItem);
		return dto;
	}

	@Override
	public Menu updateMenu(Menu dto) {
		Menu updated = menuDao.update(dto);
		List<Item> updateItems = updateItems(dto.getMenuItems());
		updated.setMenuItems(updateItems);
		return updated;
	}

	@Override
	public List<Menu> fetchMenus() {
		List<Menu> menus = menuDao.fetchMenus();
		for (Menu menu : menus) {
			if (menu == null)
				return menus;
			menu.setMenuItems(fetchItems(menu.getId()));
		}
		return menus;
	}

	@Override
	public List<Item> fetchItems(int dto_id) {
		return itemDao.fetchMenuItems(dto_id);
	}

	@Override
	public List<Item> addItems(List<Item> dtos) {
		for (Item dto : dtos) {
			dto = itemDao.save(dto);
		}
		return dtos;
	}

	@Override
	public List<Item> deleteItems(List<Item> dtos) {
		for (Item dto : dtos) {
			dto = itemDao.delete(dto);
		}
		return dtos;
	}

	@Override
	public List<Item> updateItems(List<Item> dtos) {
		for (Item dto : dtos) {
			dto = itemDao.update(dto);
		}
		return dtos;
	}

}
