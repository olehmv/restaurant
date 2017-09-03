package restor.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restor.dao.item.IItemDAO;
import restor.dao.order.IOrderDAO;
import restor.dto.item.Item;
import restor.dto.order.Order;

@Component
public class OrderService implements IOrderService {
	@Autowired
	private IOrderDAO orderDao;
	@Autowired
	private IItemDAO itemDao;

	@Override
	public Order addOrder(Order dto) {
		List<Item> items = dto.getOrderItems();
		dto = orderDao.save(dto);
		for (Item item : items) {
			item.setOrder_id(dto.getId());
			item = itemDao.save(item);
		}
		dto.setOrderItems(items);
		return dto;
	}

	@Override
	public Order updateOrder(Order dto) {
			List<Item> items = dto.getOrderItems();
			dto = orderDao.update(dto);
			for (Item item : items) {
				item.setOrder_id(dto.getId());
				if (isItemNew(item)) {
					item = itemDao.save(item);
				} else {
					item = itemDao.update(item);
				}
			}
			dto.setOrderItems(items);
		return dto;
	}

	@Override
	public Order deleteOrder(Order dto) {
		List<Item> items = dto.getOrderItems();
		orderDao.delete(dto);
		for (Item item : items) {
			itemDao.delete(item);
		}
		return dto;
	}

	@Override
	public Order fetchOrder(int dto_id) {
		Order dto = orderDao.fetchOrder(dto_id);
		if (dto == null)
			return dto;
		dto.setOrderItems(itemDao.fetchOrderItems(dto_id));
		return dto;
	}

	@Override
	public List<Order> fetchOrders() {
		List<Order> orders = orderDao.fetchOrders();
		for (Order order : orders) {
			order.setOrderItems(itemDao.fetchOrderItems(order.getId()));
		}
		return orders;
	}

	private boolean isItemNew(Item item) {
		Item fetchItem = itemDao.fetchItem(item.getId());
		if (fetchItem == null)
			return true;
		if (!fetchItem.equals(item))
			return true;
		return false;
	}
}
