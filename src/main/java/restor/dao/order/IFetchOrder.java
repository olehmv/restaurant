package restor.dao.order;

import restor.dto.order.Order;

public interface IFetchOrder {
	/**
	 * fetch order from table
	 * 
	 * @param dto_id
	 * @return Order
	 */
	Order fetchOrder(int dto_id);
}
