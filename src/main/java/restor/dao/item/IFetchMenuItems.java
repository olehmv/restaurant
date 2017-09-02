package restor.dao.item;

import java.util.List;

import restor.dto.item.Item;

public interface IFetchMenuItems {

	List<Item> fetchMenuItems(int dto_id);

}
