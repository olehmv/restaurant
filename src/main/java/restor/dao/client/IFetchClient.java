package restor.dao.client;

import restor.dto.client.Client;

public interface IFetchClient {
	Client fetchClient(int dto_id);
}
