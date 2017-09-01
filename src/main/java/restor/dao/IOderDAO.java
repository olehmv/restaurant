package restor.dao;

import restor.dto.order.Order;

public interface IOderDAO extends IFetchItems {
	
	Order save(Order dto);

	Order update(Order dto);

	
	Order delete(Order dto);
	

}
