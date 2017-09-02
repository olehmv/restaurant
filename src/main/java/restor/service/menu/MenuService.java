package restor.service.menu;

import java.util.List;

import org.springframework.stereotype.Component;

import restor.dao.item.IItemDAO;
import restor.dao.menu.IMenuDAO;
import restor.dto.item.Item;
import restor.dto.menu.Menu;
@Component
public class MenuService implements IMenuService {
	private IMenuDAO menuDao;
	private IItemDAO itemDao;

	@Override
	public Menu fetchMenu(int dto_id) {
		return menuDao.fetchMenu(dto_id);
	}

	@Override
	public Menu addMenu(Menu dto) {
		return menuDao.save(dto);
	}

	@Override
	public Menu deleteMenu(Menu dto) {
		return menuDao.delete(dto);
	}

	@Override
	public Menu updateMenu(Menu dto) {
		return menuDao.update(dto);
	}

	@Override
	public List<Menu> fetchMenus() {
		return menuDao.fetchMenus();
	}

	@Override
	public List<Item> fetchItems(int dto_id) {
		return itemDao.fetchMenuItems(dto_id);
	}


	

}
