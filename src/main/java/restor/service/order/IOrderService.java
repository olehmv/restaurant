package restor.service.order;

import java.util.List;

import restor.dto.order.Order;

public interface IOrderService {

	Order addOrder(Order dto);
	Order updateOrder(Order dto);
	Order deleteOrder(Order dto);
	Order fetchOrder(int dto_id);
	List<Order>fetchOrders();
	
}
