package restor.dao;

import java.util.List;

import restor.dto.order.Order;

public interface IFetchOrders {
	List<Order> fetchOrders(int dto_id);
}
