package restor.service.order;

import java.util.List;

import org.springframework.stereotype.Component;

import restor.dao.order.IOderDAO;
import restor.dto.order.Order;
@Component
public class OrderService  implements IOrderService{
	private IOderDAO orderDao;
	@Override
	public Order addOrder(Order dto) {
		return orderDao.save(dto);
	}

	@Override
	public Order updateOrder(Order dto) {
		return orderDao.update(dto);
	}

	@Override
	public Order deleteOrder(Order dto) {
		return orderDao.delete(dto);
	}

	@Override
	public Order fetchOrder(int dto_id) {
		return orderDao.fetchOrder(dto_id);
	}

	@Override
	public List<Order> fetchOrders() {
		return orderDao.fetchOrders();
	}

}
