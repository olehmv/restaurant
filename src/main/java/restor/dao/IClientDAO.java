package restor.dao;

import restor.dto.client.Client;

public interface IClientDAO extends IFetchOrders{
	
	Client save(Client dto);

	Client update(Client dto);
	
	Client delete(Client dto);

}
