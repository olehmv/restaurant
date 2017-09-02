package restor.dao.order;

import restor.dto.order.Order;

public interface IOderDAO extends IFetchOrders,IFetchOrder {
	
	Order save(Order dto);

	Order update(Order dto);

	
	Order delete(Order dto);
	

}
