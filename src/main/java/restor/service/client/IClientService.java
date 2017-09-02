package restor.service.client;

import java.util.List;

import restor.dto.client.Client;

public interface IClientService {
	Client addClient(Client dto);
	Client updateClient(Client dto);
	Client deleteClient(Client dto);
	Client fetchClient(int dto_id);
	List<Client>fetchClients(int dto_id);
}
