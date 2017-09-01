package restor.dto.item;

import java.util.List;

import restor.dto.order.Order;

public class DishItem extends Item {

	

	@Override
	public double price() {
		return getPrice();
	}

	
	
}
