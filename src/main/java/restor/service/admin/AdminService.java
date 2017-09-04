package restor.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restor.dao.admin.IAdminDAO;
import restor.dao.item.IItemDAO;
import restor.dao.order.IOrderDAO;
import restor.dto.admin.Admin;
import restor.dto.item.Item;
import restor.dto.order.Order;

@Component
public class AdminService implements IAdminService {
	@Autowired
	private IAdminDAO adminDao;
	@Autowired
	private IOrderDAO orderDao;
	@Autowired
	private IItemDAO itemDao;

	@Override
	public Admin addAdmin(Admin dto) {
		return adminDao.save(dto);
	}

	@Override
	public Admin updateAdmin(Admin dto) {
		List<Order> orders = dto.getAdminOrders();
		for (Order order : orders) {
			order = orderDao.update(order);
			List<Item> orderItems = itemDao.fetchOrderItems(order.getId());
			orderItems.addAll(order.getOrderItems());
			order.setOrderItems(orderItems);
			order.process();
			orderDao.update(order);
		}
		return adminDao.update(dto);
	}

	@Override
	public Admin deleteAdmin(Admin dto) {
		List<Order> orders = dto.getAdminOrders();
		for (Order order : orders) {
			order.setAdmin_id(0);
			orderDao.update(order);
		}
		return adminDao.delete(dto);
	}

	@Override
	public Admin fetchAdmin(int dto_id) {
		Admin dto = adminDao.fetchAdmin(dto_id);
		if (dto == null)
			return dto;
		List<Order> orders = fetchOrders(dto_id);
		dto.setAdminOrders(orders);
		return dto;

	}

	@Override
	public List<Order> fetchOrders(int dto_id) {
		List<Order> orders = orderDao.fetchAdminOrders(dto_id);
		for (Order order : orders) {
			List<Item> items = itemDao.fetchOrderItems(order.getId());
			order.setOrderItems(items);
			order.process();
			orderDao.update(order);
		}
		return orders;
	}

	@Override
	public List<Admin> fetchAdmins() {
		List<Admin> admins = adminDao.fetchAdmins();
		for (Admin admin : admins) {
			List<Order> orders = fetchOrders(admin.getId());
			admin.setAdminOrders(orders);
		}
		return admins;
	}

}
