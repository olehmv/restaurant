package restor.dao;

import restor.dto.item.Item;

public interface IItemDAO {

	Item save(Item dto);

	Item update(Item dto);

	Item delete(Item dto);

}
