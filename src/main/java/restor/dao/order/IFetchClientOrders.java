package restor.dao.order;

import java.util.List;

import restor.dto.item.Item;
import restor.dto.order.Order;

public interface IFetchClientOrders {
	
	List<Order> fetchClientOrders(int dto_id);

}
