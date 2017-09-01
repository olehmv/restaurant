package restor.dao;

import restor.dto.menu.Menu;

public interface IMenuDAO extends IFetchItems{
	Menu save(Menu dto);

	Menu update(Menu dto);

	Menu delete(Menu dto);

}
