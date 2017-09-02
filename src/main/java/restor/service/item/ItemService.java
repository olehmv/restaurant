package restor.service.item;

import java.util.List;

import org.springframework.stereotype.Component;

import restor.dao.item.IItemDAO;
import restor.dto.item.Item;
@Component
public class ItemService implements IItemService{
	IItemDAO itemDao;

	@Override
	public List<Item> fetchItems() {
		return itemDao.fetchItems();
	}

	@Override
	public Item fetchItem(int dto_id) {
		return itemDao.fetchItem(dto_id);
	}

	@Override
	public Item addItem(Item dto) {
		return itemDao.save(dto);
	}

	@Override
	public Item updateItem(Item dto) {
		return itemDao.update(dto);
	}

	@Override
	public Item deleteItem(Item dto) {
		return itemDao.delete(dto);
	}

}
