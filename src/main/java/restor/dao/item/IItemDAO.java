package restor.dao.item;

import restor.dto.item.Item;

public interface IItemDAO extends IFetchItems,IFetchOrderItems,IFetchMenuItems,IFetchItem{

	Item save(Item dto);

	Item update(Item dto);

	Item delete(Item dto);

}
