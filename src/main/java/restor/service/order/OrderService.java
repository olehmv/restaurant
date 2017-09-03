package restor.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restor.dao.item.IItemDAO;
import restor.dao.order.IOrderDAO;
import restor.dto.order.Order;

@Component
public class OrderService implements IOrderService {
	@Autowired
	private IOrderDAO orderDao;
	@Autowired
	private IItemDAO itemDao;

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
		Order order = orderDao.fetchOrder(dto_id);
		order.setOrderItems(itemDao.fetchOrderItems(dto_id));
		return  order;   
	}

	@Override
	public List<Order> fetchOrders() {
		List<Order> orders = orderDao.fetchOrders();
		for (Order order : orders) {
			order.setOrderItems(itemDao.fetchOrderItems(order.getId()));
		}
		return orders;
	}

}
