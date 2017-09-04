package restor.service.client;

import java.util.List;

import restor.dto.client.Client;
import restor.dto.order.Order;

public interface IClientService {
	Client addClient(Client dto);

	/**
	 * Update Client
	 * 
	 * @param Client
	 * @return updated Client
	 */
	Client updateClient(Client dto);

	Client deleteClient(Client dto);

	Client fetchClient(int dto_id);

	List<Client> fetchClients();

	public List<Order> fetchOrders(int dto_id);
}
