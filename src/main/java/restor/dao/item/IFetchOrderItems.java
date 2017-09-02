package restor.dao.item;

import java.util.List;

import restor.dto.item.Item;

public interface IFetchOrderItems {
	
	List<Item> fetchOrderItems(int dto_id);

}
