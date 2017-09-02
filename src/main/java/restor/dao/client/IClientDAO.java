package restor.dao.client;

import restor.dto.client.Client;

public interface IClientDAO extends IFetchClient,IFetchClients{
	
	Client save(Client dto);

	Client update(Client dto);
	
	Client delete(Client dto);

}
