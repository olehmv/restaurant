package restor.service.item;

import java.util.List;

import restor.dto.item.Item;

public interface IItemService {
	List<Item> fetchItems();

	Item fetchItem(int dto_id);

	Item addItem(Item dto);

	Item updateItem(Item dto);

	Item deleteItem(Item dto);
}
