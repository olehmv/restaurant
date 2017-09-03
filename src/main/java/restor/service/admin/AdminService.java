package restor.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restor.dao.admin.IAdminDAO;
import restor.dao.item.IItemDAO;
import restor.dao.order.IOrderDAO;
import restor.dto.admin.Admin;
import restor.dto.item.Item;
import restor.dto.menu.Menu;
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
		List<Order> orders = dto.getOrders();
		for (Order order : orders) {
			order = orderDao.update(order);
			List<Item> orderItems = itemDao.fetchOrderItems(order.getId());
			order.setOrderItems(orderItems);
		}
		return adminDao.update(dto);
	}

	@Override
	public Admin deleteAdmin(Admin dto) {
		List<Order> orders = dto.getOrders();
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
		for (Order order : orders) {
			List<Item> items = itemDao.fetchOrderItems(order.getId());
			order.setOrderItems(items);
		}
		dto.setOrders(orders);
		return dto;

	}

	@Override
	public List<Order> fetchOrders(int dto_id) {
		return orderDao.fetchAdminOrders(dto_id);
	}

	@Override
	public List<Admin> fetchAdmins() {
		List<Admin> admins = adminDao.fetchAdmins();
		for (Admin admin : admins) {
			List<Order> orders = fetchOrders(admin.getId());
			for (Order order : orders) {
				List<Item> items = itemDao.fetchOrderItems(order.getId());
				order.setOrderItems(items);
			}
			admin.setOrders(orders);
		}
		return admins;
	}

}
