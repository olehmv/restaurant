package restor.dto.item;

public class OrderItem extends Item {

	@Override
	public double price() {
		return getPrice();
	}

	

}
