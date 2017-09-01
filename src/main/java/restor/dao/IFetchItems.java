package restor.dao;

import java.util.List;

import restor.dto.item.Item;

public interface IFetchItems {
	
	List<Item>fetchItems(int dto_id);


}
