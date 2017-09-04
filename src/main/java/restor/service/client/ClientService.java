package restor.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import restor.dao.client.IClientDAO;
import restor.dao.item.IItemDAO;
import restor.dao.order.IOrderDAO;
import restor.dto.client.Client;
import restor.dto.item.Item;
import restor.dto.order.Order;

@Component
public class ClientService implements IClientService {
	@Autowired
	private IClientDAO clientDao;
	@Autowired
	private IOrderDAO orderDao;
	@Autowired
	private IItemDAO itemDao;

	@Override
	public Client addClient(Client dto) {
		List<Order> orders = dto.getClientOrders();
		dto = clientDao.save(dto);
		if (orders == null)
			return dto;
		for (Order order : orders) {
			if (order == null)
				continue;
			List<Item> items = order.getOrderItems();
			order.setClient_id(dto.getId());
			order = orderDao.save(order);
			for (Item item : items) {
				item.setOrder_id(order.getId());
				item = itemDao.save(item);
			}
			order.setOrderItems(items);
			order.process();
			orderDao.update(order);

		}
		dto.setClientOrders(orders);
		return dto;
	}

	@Override
	public Client updateClient(Client dto) {
		List<Order> orders = dto.getClientOrders();
		dto = clientDao.update(dto);
		for (Order order : orders) {
			List<Item> items = order.getOrderItems();
			order.setClient_id(dto.getId());
			if (isOrderNew(order)) {
				order = orderDao.save(order);
			} else {
				order = orderDao.update(order);
			}
			for (Item item : items) {
				item.setOrder_id(order.getId());
				if (isItemNew(item)) {
					item = itemDao.save(item);
				} else {
					item = itemDao.update(item);
				}
			}
			order.setOrderItems(items);
			order.process();
			orderDao.update(order);
		}
		dto.setClientOrders(orders);
		return dto;
	}

	private boolean isOrderNew(Order order) {
		boolean isNew = false;
		List<Order> fetchClientOrders = orderDao.fetchClientOrders(order.getClient_id());
		for (Order dto : fetchClientOrders) {
			if (!dto.equals(order))
				isNew = true;
		}
		return isNew;
	}

	private boolean isItemNew(Item item) {
		Item fetchItem = itemDao.fetchItem(item.getId());
		if (fetchItem == null)
			return true;
		if (!fetchItem.equals(item))
			return true;
		return false;
	}

	@Override
	public Client deleteClient(Client dto) {
		clientDao.delete(dto);
		List<Order> orders = orderDao.fetchClientOrders(dto.getId());
		for (Order order : orders) {
			orderDao.delete(order);
			List<Item> items = itemDao.fetchOrderItems(order.getId());
			for (Item item : items) {
				itemDao.delete(item);
			}
		}
		return dto;
	}

	@Override
	public Client fetchClient(int dto_id) {
		Client dto = clientDao.fetchClient(dto_id);
		List<Order> orders = fetchOrders(dto_id);
		dto.setClientOrders(orders);
		return dto;
	}

	@Override
	public List<Client> fetchClients() {
		List<Client> clients = clientDao.fetchClients();
		for (Client client : clients) {
			List<Order> orders = fetchOrders(client.getId());
			client.setClientOrders(orders);
		}
		return clients;
	}

	@Override
	public List<Order> fetchOrders(int dto_id) {
		List<Order> orders = orderDao.fetchClientOrders(dto_id);
		for (Order order : orders) {
			if (order == null)
				continue;
			List<Item> items = itemDao.fetchOrderItems(order.getId());
			order.setOrderItems(items);
			order.process();
			orderDao.update(order);
		}
		return orders;
	}

}
