package restor.dao.order;

import java.util.List;

import restor.dto.order.Order;

public interface IFetchOrders {
	List<Order> fetchOrders();
}
