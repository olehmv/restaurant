package restor.dao.order;

import restor.dto.order.Order;

public interface IOrderDAO extends IFetchOrders, IFetchOrder, IFetchAdminOrders, IFetchClientOrders {

	Order save(Order dto);

	Order update(Order dto);

	Order delete(Order dto);

}
