package restor.service.client;

import java.util.List;

import org.springframework.stereotype.Component;

import restor.dao.client.IClientDAO;
import restor.dto.client.Client;
@Component
public class ClientService implements IClientService {
	private IClientDAO clientDao;

	@Override
	public Client addClient(Client dto) {
		return clientDao.save(dto);
	}

	@Override
	public Client updateClient(Client dto) {
		return clientDao.update(dto);
	}

	@Override
	public Client deleteClient(Client dto) {
		return clientDao.delete(dto);
	}

	@Override
	public Client fetchClient(int dto_id) {
		return clientDao.fetchClient(dto_id);
	}

	@Override
	public List<Client> fetchClients(int dto_id) {
		return clientDao.fetchClients();
	}

}
