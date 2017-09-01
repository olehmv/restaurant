package restor.dto.admin;

import java.util.List;

import org.springframework.stereotype.Component;

import restor.dto.item.Item;
import restor.dto.order.Order;
@Component
public class RestorAdmin extends Admin {

	@Override
	public Order process(String description, List<Item> items) {
		setOrder(getOrder().process(description, items));
		getOrders().add(getOrder());
		return getOrder();
	}

	@Override
	public double price() {
		return getOrder().getPrice();
	}

}
