package restor.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restor.dao.item.IItemDAO;
import restor.dto.item.Item;

@Component
public class ItemService implements IItemService {
	@Autowired
	IItemDAO itemDao;

	@Override
	public List<Item> fetchItems() {
		return itemDao.fetchItems();
	}

	@Override
	public Item fetchItem(int dto_id) {
		Item dto = itemDao.fetchItem(dto_id);
		if (dto == null)
			return dto;
		return dto;
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
