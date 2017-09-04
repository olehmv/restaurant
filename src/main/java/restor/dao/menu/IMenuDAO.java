package restor.dao.menu;

import restor.dto.menu.Menu;

public interface IMenuDAO extends IFetchMenu, IFetchMenus {
	Menu save(Menu dto);

	Menu update(Menu dto);

	Menu delete(Menu dto);

}
