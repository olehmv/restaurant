package restor.dao.order;

import java.util.List;

import restor.dto.order.Order;

public interface IFetchAdminOrders {

	List<Order> fetchAdminOrders(int dto_id);

}
